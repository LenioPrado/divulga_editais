package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;
import java.util.List;

public class Modality implements Serializable {

    private static final long serialVersionUID = 1L;

    private int modalityId;

    private String acronyms;

    private String description;

    private List<Notice> notices;

    public Modality() {
    }

    public int getModalityId() {
        return this.modalityId;
    }

    public void setModalityId(int modalityId) {
        this.modalityId = modalityId;
    }

    public String getAcronyms() {
        return this.acronyms;
    }

    public void setAcronyms(String acronyms) {
        this.acronyms = acronyms;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Notice> getNotices() {
        return this.notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public Notice addNotice(Notice notice) {
        getNotices().add(notice);
        notice.setModality(this);

        return notice;
    }

    public Notice removeNotice(Notice notice) {
        getNotices().remove(notice);
        notice.setModality(null);

        return notice;
    }
}