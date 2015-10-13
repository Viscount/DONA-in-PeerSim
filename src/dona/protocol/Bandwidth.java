package dona.protocol;

import dona.entity.Message;
import dona.entity.PendingQueue;
import dona.util.JsonUtil;
import peersim.core.Node;
import peersim.edsim.EDProtocol;
import peersim.vector.SingleValueHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by Jaric Liao on 2015/9/26.
 */
public class Bandwidth extends SingleValueHolder implements EDProtocol {

    private PendingQueue pendingQueue;
    private Map<Long,Long> capacity;

    public Bandwidth(String prefix){
        super(prefix);
    }

    @Override
    public void processEvent(Node node, int pid, Object event) {
        Message message = JsonUtil.toObject((String) event, Message.class);
        // clean pending queue
        pendingQueue.clean();
        //
    }
}
