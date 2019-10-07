package captcha;
 
import java.io.IOException;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.servlet.CaptchaServletUtil;
 
public class AudioCaptCha {
    public void getAudioCaptCha(HttpServletRequest req, HttpServletResponse resp, String answer) throws IOException
    {
        HttpSession session = req.getSession();
        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        String getAnswer = answer;
        AudioCaptcha audiocaptcha = null;
        if ( getAnswer == null || "".equals(getAnswer) ) getAnswer = captcha.getAnswer();
               audiocaptcha = new AudioCaptcha.Builder()
            //.addAnswer(new DefaultTextProducer(6, getAnswer.toCharArray()))
               .addAnswer(new SetTextProducer(getAnswer))
            .addNoise()
            .build();
               
        String agent = req.getParameter("agent"); //���������� ������ �޸��ؾ��Ұ�� �̿�.
        CaptchaServletUtil.writeAudio(resp, audiocaptcha.getChallenge());
    }
}
