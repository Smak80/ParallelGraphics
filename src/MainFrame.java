import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame{

    private final JPanel mainPanel = new JPanel(){
        @Override
        public void paint(Graphics g){
            var count = 10;
            var width = mainPanel.getWidth() / count;
            var gr = mainPanel.getGraphics();
            for (int i = 0; i < count; i++){
                StripePainter sPainter = new StripePainter(
                        gr,
                        i * width,
                        width,
                        mainPanel.getHeight()
                );
                new Thread(sPainter).start();
            }
        }

    };

    public MainFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 500));
        add(mainPanel);
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                mainPanel.repaint();
            }
        });
    }
}
