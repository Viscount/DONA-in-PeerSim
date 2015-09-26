package dona.protocol;

import peersim.core.Node;
import peersim.edsim.EDProtocol;
import peersim.vector.SingleValueHolder;

/**
 * Created by Jaric Liao on 2015/9/26.
 */
public class Bandwidth extends SingleValueHolder implements EDProtocol {

    public Bandwidth(String prefix){
        super(prefix);
    }

    @Override
    public void processEvent(Node node, int pid, Object event) {

    }
}
