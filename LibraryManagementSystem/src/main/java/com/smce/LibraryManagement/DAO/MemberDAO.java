package com.smce.LibraryManagement.DAO;


import com.smce.LibraryManagement.Model.Member;
import com.smce.LibraryManagement.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection connection;

    public MemberDAO() {
        connection = DBConnection.getConnection();
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Member member = new Member();
            member.setId(resultSet.getInt("id"));
            member.setName(resultSet.getString("name"));
            member.setEmail(resultSet.getString("email"));
            member.setPhone(resultSet.getString("phone"));
            member.setAddress(resultSet.getString("address"));
            members.add(member);
        }
        return members;
    }

    public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO members (name, email, phone, address) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, member.getName());
        preparedStatement.setString(2, member.getEmail());
        preparedStatement.setString(3, member.getPhone());
        preparedStatement.setString(4, member.getAddress());
        preparedStatement.executeUpdate();
    }

    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE members SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, member.getName());
        preparedStatement.setString(2, member.getEmail());
        preparedStatement.setString(3, member.getPhone());
        preparedStatement.setString(4, member.getAddress());
        preparedStatement.setInt(5, member.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteMember(int memberId) throws SQLException {
        String query = "DELETE FROM members WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberId);
        preparedStatement.executeUpdate();
    }
}

