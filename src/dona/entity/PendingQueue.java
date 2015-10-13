package dona.entity;

import peersim.core.CommonState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/10/13.
 */
public class PendingQueue {

    private long schedule_time;
    private List<Message> detail;
    private List<Long> completeTime;

    public PendingQueue(){
        this.detail = new ArrayList<Message>();
        this.completeTime = new ArrayList<Long>();
    }

    public void addMessage(Message message, long transport_time){
        this.detail.add(message);
        long new_schedule_time = 0;
        if (( completeTime.size() == 0 ) || ( schedule_time < CommonState.getTime())) new_schedule_time = CommonState.getTime() + transport_time;
        else new_schedule_time = schedule_time + transport_time;
        this.completeTime.add(new_schedule_time);
        this.schedule_time = new_schedule_time;
    }

    public void clean(){
        for (int i=0; i<detail.size(); i++){
            if ( completeTime.get(i) < CommonState.getTime() ){
                detail.remove(i);
                completeTime.remove(i);
            }
        }
    }

    public long getScheduleTime(){
        return schedule_time;
    }
}
