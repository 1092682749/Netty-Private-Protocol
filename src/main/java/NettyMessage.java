import java.util.HashMap;
import java.util.Map;

public class NettyMessage {
    private byte type;
    private Map<String, Object> attachment = new HashMap<String, Object>();

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}
