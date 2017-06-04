package com.Test.unziper;

/**
 * Created by olmer on 15.04.17.
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BatchWriter extends JdbcDaoSupport implements Consumer<String[]> {
    @Autowired
    DataSource systemDataSource;

    //        Loggerlogger=Logger.getLogger(getClass());
    String destinationTablePath;

    List<String[]> listPreparedData;

    int counter = 0;

    String query;

    public void initTable(String destinationSchema,
                          String destinationTable) {
        String destinationTablePath = destinationTable;

        if (StringUtils.isNoneEmpty(destinationSchema)) {
            destinationTablePath = String.format("%s.%s", destinationSchema, destinationTable);
        }
        query = String.format("INSERT INTO %s.%s (filename,data) VALUES (?,?)", destinationSchema, destinationTable);
    }


    public void selectLines() {
        Connection conn = null;
        System.out.println(systemDataSource);
        try {
            conn = systemDataSource.getConnection();
            String query = String.format("select count(*) COUNT from" + destinationTablePath);
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt("COUNT"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLine(String[] data) {
        if (listPreparedData == null) {
            listPreparedData = new ArrayList<>();
        }
        if (data.length == 2) { // TODO report to log if length differ
            listPreparedData.add(data);
            counter++;
        }
        if (listPreparedData.size() == 10) {
            System.out.println("Start batch data, " + counter + " lines loaded");
            getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, listPreparedData.get(i)[0]);
                    ps.setString(2, listPreparedData.get(i)[1]);
                }

                @Override
                public int getBatchSize() {
                    return listPreparedData.size();
                }
            });
            listPreparedData = null;
        }


    }


    public void close() {
        releaseConnection(getConnection());
    }

    @PostConstruct
    public void init() {
        setDataSource(systemDataSource);
    }

    @Override
    public void accept(String[] strings) {
        insertLine(strings);
    }
}