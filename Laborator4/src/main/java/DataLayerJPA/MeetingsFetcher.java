package DataLayerJPA;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "meetingFetcher")
@SessionScoped
public class MeetingsFetcher {
    @PersistenceContext
    private EntityManager em;
    private List<MeetingsJPA> meetings;

    public List<MeetingsJPA> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingsJPA> meetings) {
        this.meetings = meetings;
    }

    public String filterMeetings(MeetingFilter filter)
    {
        CriteriaBuilder builder=em.getCriteriaBuilder();

        CriteriaQuery<MeetingsJPA> query=builder.createQuery(MeetingsJPA.class);

        Root<MeetingsJPA> root=query.from(MeetingsJPA.class);
        List<Predicate> conditions=new ArrayList<>();
        if(filter.byTopic)
        {
            conditions.add(builder.equal(root.get("topic"),filter.topic));
        }
        if(filter.byDurationsLessThan)
        {
            conditions.add(builder.lt(root.get("duration"),filter.durationLessThan));
        }
        if(filter.byDurationsGreaterThan)
        {
            conditions.add(builder.gt(root.get("duration"),filter.durationGreaterThan));
        }
        if(filter.byLocation)
        {
            conditions.add(builder.like(root.get("locationsByLocationId").get("name"),"%"+filter.location+"%"));
        }
        if(filter.byDate)
        {
            String dateFilter=new SimpleDateFormat("yyyy-MM-dd").format(filter.getDate()).trim();
            conditions.add(builder.like(root.get("startTime"),"%"+dateFilter+"%"));
        }
        if(conditions.size()>0)
        {
            Predicate completeFilter=conditions.get(0);
            for(int i=1;i<conditions.size();i++)
            {
                completeFilter=builder.and(completeFilter,conditions.get(i));
            }
            query.where(completeFilter);
        }
        TypedQuery<MeetingsJPA> q=em.createQuery(query);
        this.meetings=q.getResultList();
        return "filteredMeetings";
    }
}
