package nsi.schoolplanner.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinTimetableWithSubject {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private Long timetableId;

    @NotNull
    private Long subjectId;

    @NotNull
    private int row; //redni broj casa

    @Generated(hash = 508225987)
    public JoinTimetableWithSubject(Long id, @NotNull Long timetableId,
            @NotNull Long subjectId, int row) {
        this.id = id;
        this.timetableId = timetableId;
        this.subjectId = subjectId;
        this.row = row;
    }

    @Generated(hash = 502395198)
    public JoinTimetableWithSubject() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimetableId() {
        return this.timetableId;
    }

    public void setTimetableId(Long timetableId) {
        this.timetableId = timetableId;
    }

    public Long getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
