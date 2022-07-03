/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.jfreechart.piechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlotState;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Rodrigo
 */
public final class PieChart extends javax.swing.JFrame {

    private CustomDonutPlot piePlot;

    public PieChart() {
        initComponents();

        ChartPanel panel = criarPieChart();
        add(panel);
    }

    private ChartPanel criarPieChart() {
        piePlot = new CustomDonutPlot(datasetInicial());

        piePlot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        piePlot.setSectionDepth(0.30);

        String title = "Vendas por Forma de Pagamento";
        JFreeChart chartPieSeries = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, piePlot, true);

        piePlot.setBackgroundPaint(Color.WHITE);
//        rPlot.setCenterText(null);
//        rPlot.setLabelGenerator(null);
        piePlot.setOutlineVisible(false);
        piePlot.setShadowGenerator(null);
        piePlot.setShadowPaint(null);
        piePlot.setSectionOutlinesVisible(false);
        piePlot.setOuterSeparatorExtension(3);
        piePlot.setInnerSeparatorExtension(4);

        // Set colors of the chart
        piePlot.setSectionPaint("DINHEIRO", new Color(120, 224, 143));
        piePlot.setSectionPaint("BOLETO", new Color(74, 105, 189));
        piePlot.setSectionPaint("CREDIARIO", new Color(96, 163, 188));

        piePlot.setSeparatorPaint(Color.WHITE);
        piePlot.setSeparatorStroke(new BasicStroke(4.0f));
        piePlot.setSeparatorsVisible(true);

        piePlot.setExplodePercent("DINHEIRO", 0.05);
        piePlot.setExplodePercent("CREDIARIO", 0.05);
        piePlot.setExplodePercent("BOLETO", 0.05);

        chartPieSeries.setBackgroundPaint(new Color(0, 0, 0, 0));

        chartPieSeries.setPadding(new RectangleInsets(5, 5, 5, 5));

        chartPieSeries.setTitle(new TextTitle(title, new Font("Segoe UI", Font.PLAIN, 14)));
        chartPieSeries.getTitle().setBackgroundPaint(Color.WHITE);
        chartPieSeries.setBorderStroke(new BasicStroke(0f));
        chartPieSeries.setBorderVisible(false);

        PiePlot plot = (PiePlot) chartPieSeries.getPlot();

        plot.setBackgroundPaint(Color.white);
        plot.setShadowGenerator(null);
        plot.setIgnoreNullValues(true);
        plot.setLegendItemShape(new Rectangle(8, 6));
        plot.setOutlineVisible(false);

        plot.setLabelBackgroundPaint(Color.white);

        LegendTitle legend = chartPieSeries.getLegend();
        legend.setFrame(BlockBorder.NONE);
        legend.setPosition(RectangleEdge.BOTTOM);
        legend.setVerticalAlignment(VerticalAlignment.TOP);

        ChartPanel chartPanel = new ChartPanel(chartPieSeries);
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    }

    public class CustomDonutPlot extends RingPlot {

        private static final long serialVersionUID = 1L;

        public CustomDonutPlot(DefaultPieDataset dataSet) {
            super(dataSet);
        }

        @Override
        protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea, PiePlotState state, int currentPass) {
            super.drawItem(g2, section, dataArea, state, currentPass);
        }

        @Override
        protected Rectangle2D getArcBounds(Rectangle2D unexploded, Rectangle2D exploded, double angle, double extent, double explodePercent) {
            if (explodePercent > 0.0) {
                this.setSectionDepth(0.33);//to match inner arc
                java.awt.geom.Arc2D.Double arc1 = new java.awt.geom.Arc2D.Double(unexploded, angle, extent / 2.0D, 0);
                Point2D point1 = arc1.getEndPoint();
                //java.awt.geom.Arc2D.Double arc2 = new java.awt.geom.Arc2D.Double(exploded, angle, extent / 2.0D, 0); //original code
                Rectangle2D mix = new Rectangle2D.Double(exploded.getX(), exploded.getY(), unexploded.getWidth(), unexploded.getHeight());
                java.awt.geom.Arc2D.Double arc2 = new java.awt.geom.Arc2D.Double(mix, angle, extent / 2.0D, 0);

                Point2D point2 = arc2.getEndPoint();
                double deltaX = (point1.getX() - point2.getX()) * explodePercent;
                double deltaY = (point1.getY() - point2.getY()) * explodePercent;
                //return new java.awt.geom.Rectangle2D.Double(unexploded.getX() - deltaX, unexploded.getY() - deltaY, unexploded.getWidth(), unexploded.getHeight()); original code
                return new java.awt.geom.Rectangle2D.Double(unexploded.getX() - deltaX, unexploded.getY() - deltaY, exploded.getWidth(), exploded.getHeight());
            } else {
                this.setSectionDepth(0.3);//default depth
                return super.getArcBounds(unexploded, exploded, angle, extent, explodePercent);
            }
        }

    }

    private DefaultPieDataset datasetInicial() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("DINHEIRO", 50);
        dataset.setValue("CREDIARIO", 80);
        dataset.setValue("PIX", 20);
        dataset.setValue("BOLETO", 7);
        dataset.setValue("OUTROS", 3);
        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpSouth = new javax.swing.JPanel();
        jbTrocarDataset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pie Chart");
        setMinimumSize(new java.awt.Dimension(600, 400));

        jpSouth.setPreferredSize(new java.awt.Dimension(400, 50));
        jpSouth.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 15));

        jbTrocarDataset.setText("Randomizar Dataset");
        jbTrocarDataset.setFocusPainted(false);
        jbTrocarDataset.setFocusable(false);
        jbTrocarDataset.setPreferredSize(new java.awt.Dimension(300, 25));
        jbTrocarDataset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTrocarDatasetActionPerformed(evt);
            }
        });
        jpSouth.add(jbTrocarDataset);

        getContentPane().add(jpSouth, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbTrocarDatasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTrocarDatasetActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("DINHEIRO", new Random().nextInt(50));
        dataset.setValue("CREDIARIO", new Random().nextInt(100));
        dataset.setValue("PIX", new Random().nextInt(30));
        dataset.setValue("BOLETO", new Random().nextInt(60));
        dataset.setValue("OUTROS", new Random().nextInt(40));

        piePlot.setDataset(dataset);
    }//GEN-LAST:event_jbTrocarDatasetActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PieChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new PieChart().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbTrocarDataset;
    private javax.swing.JPanel jpSouth;
    // End of variables declaration//GEN-END:variables
}
