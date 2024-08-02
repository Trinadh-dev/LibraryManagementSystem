package com.smce.LibraryManagement.Services;



import java.sql.SQLException;
import java.util.List;

import com.smce.LibraryManagement.DAO.MemberDAO;
import com.smce.LibraryManagement.Model.Member;

public class MemberService {
    private MemberDAO memberDAO;

    public MemberService() {
        memberDAO = new MemberDAO();
    }

    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.getAllMembers();
    }

    public void addMember(Member member) throws SQLException {
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) throws SQLException {
        memberDAO.updateMember(member);
    }

    public void deleteMember(int memberId) throws SQLException {
        memberDAO.deleteMember(memberId);
    }
}

