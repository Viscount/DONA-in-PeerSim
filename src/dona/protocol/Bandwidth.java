package dona.protocol;

import dona.entity.Message;
import dona.entity.PendingQueue;
import dona.util.JsonUtil;
import peersim.config.Configuration;
import peersim.core.Network;
import peersim.core.Node;
import peersim.edsim.EDProtocol;
import peersim.edsim.EDSimulator;
import peersim.vector.SingleValueHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by Jaric Liao on 2015/9/26.
 */
public class Bandwidth extends SingleValueHolder implements EDProtocol {

    private static String PAR_PROT_INF = "inf_protocol";

    private static int pid_inf;

    private PendingQueue pendingQueue;
    private Map<Long,Long> capacity;

    public Bandwidth(String prefix){
        super(prefix);
        pid_inf = Configuration.getPid(prefix + "." + PAR_PROT_INF);
    }

    @Override
    public void processEvent(Node node, int pid, Object event) {
        Message message = JsonUtil.toObject((String) event, Message.class);
        // clean pending queue
        pendingQueue.clean();
        // calculate transport time
        long transportTime = 0;
        // add current message
        pendingQueue.addMessage(message,transportTime);
        long scheduleTime = pendingQueue.getScheduleTime();
        // send message
        EDSimulator.add(0,message, Network.get( Integer.parseInt(String.valueOf(message.getReceiver()))),pid_inf);
    }
}
