package captcha;
 
import static nl.captcha.Captcha.NAME;
 
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
 
public class CaptCha extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static int _width = 150; //�̹��� ����ũ��
    private static int _height = 50; //�̹��� ����
    private static int _fontsize = 44; //��Ʈũ��
 
    public CaptCha() {
        super();
    }
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        getCaptCha(request, response);
    }
 
    public void getCaptCha(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
                
            // ��Ʈ ���� =========================================================
            List<Font> fontList = new ArrayList<Font>();
            fontList.add(new Font("", Font.HANGING_BASELINE, 40));//
            fontList.add(new Font("Courier", Font.ITALIC, 40));
            fontList.add(new Font("", Font.PLAIN, 40));
 
            List<Color> colorList = new ArrayList<Color>();
            // colorList.add(Color.green);
            // colorList.add(Color.pink);
            // colorList.add(Color.gray);
            colorList.add(Color.black);
            // colorList.add(Color.blue);
            // ��Ʈ ���� =========================================================
 
            Captcha captcha = new Captcha.Builder( _width, _height)
                    // .addText(wordRenderer)                    
                    .addText(new NumbersAnswerProducer(6), //6�ڸ� ���ڷ� �� ���ڸ� �߰�
                    new DefaultWordRenderer(colorList, fontList)) //���� �ٹ̱�(����, ��Ʈ)
                    .gimp(new DropShadowGimpyRenderer()).gimp()
                    // BlockGimpyRenderer,FishEyeGimpyRenderer,RippleGimpyRenderer,ShearGimpyRenderer,StretchGimpyRenderer
                    .addNoise().addNoise().addBorder()
                    .addBackground(new GradiatedBackgroundProducer()) 
                    // FlatColorBackgroundProducer,SquigglesBackgroundProducer,TransparentBackgroundProducer
                    .build();
 
            req.getSession().setAttribute(NAME, captcha);
            CaptchaServletUtil.writeImage(resp, captcha.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
