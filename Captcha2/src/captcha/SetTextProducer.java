package captcha;
 
import nl.captcha.text.producer.TextProducer;
 
/**
 * ���޹��� ���ڿ��� �״�� �����ĸ���� �̿��Ҽ��ֵ��� ������ Ŭ���� 
 */
public class SetTextProducer  implements TextProducer {
 
 
    private final String _srcStr;
 
    public SetTextProducer(String srcStr) {    
        _srcStr = srcStr; 
    }
 
    @Override
    public String getText() {        
        return _srcStr;
    }
}
