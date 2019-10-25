package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;

public class UsersNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userNoticeId;

    private Notice notice;

    private User user;

    public UsersNotice() {
    }

    public int getUserNoticeId() {
        return this.userNoticeId;
    }

    public void setUserNoticeId(int userNoticeId) {
        this.userNoticeId = userNoticeId;
    }

    public Notice getNotice() {
        return this.notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}