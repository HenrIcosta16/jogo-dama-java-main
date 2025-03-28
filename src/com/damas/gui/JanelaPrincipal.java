package com.damas.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.damas.objetos.itensJogo.Jogo;
import com.damas.objetos.itensJogo.StatusJogo;

/**
 * Tela do jogo.
 * Responsável por reagir aos cliques feitos pelo jogador.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Arthur Miranda Tavares {@link arthur.miranda@academico.ufpb.br}
 */

public class JanelaPrincipal extends JFrame {

    private Jogo jogo;
    private StatusJogo statusjogo;
    private boolean primeiroClique;
    private CasaGUI casaClicadaOrigem;
    private CasaGUI casaClicadaDestino;

    public JanelaPrincipal() {

        initComponents();
        this.primeiroClique = true;
        this.casaClicadaOrigem = null;
        this.casaClicadaDestino = null;
        criarNovoJogo();

        // configura action listener para o menu novo
        menuNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarNovoJogo();
            }
        });

        // configura action listener para o menu status
        menuStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, statusjogo.toString());
            }
        });

        // configura action listener para o menu status
        menuStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, statusjogo.toString());
            }
        });

        // configura action listener para o menu sair
        menuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.pack();
    }

    /**
     * Responde aos cliques realizados no tabuleiro.
     * 
     * @param casaClicada Casa que o jogador clicou.
     */
    public void reagir(CasaGUI casaClicada) {
        if (primeiroClique) {
            if (casaClicada.possuiPeca()) {
                casaClicadaOrigem = casaClicada;
                casaClicadaOrigem.destacar();
                primeiroClique = false;
            } else {
                JOptionPane.showMessageDialog(this, "Clique em uma peça.");
            }
        } else {
            casaClicadaDestino = casaClicada;
            jogo.moverPeca(casaClicadaOrigem.getPosicaoX(), casaClicadaOrigem.getPosicaoY(),
                    casaClicadaDestino.getPosicaoX(), casaClicadaDestino.getPosicaoY());
            casaClicadaOrigem.atenuar();
            primeiroClique = true;
            atualizar();
        }

        ver_se_acabou();
    }

    /**
     * Cria um novo jogo e atualiza o tabuleiro gráfico.
     */
    private void criarNovoJogo() {
        if (!primeiroClique) {
            primeiroClique = true;
            casaClicadaOrigem.atenuar();
        }
        jogo = new Jogo();
        statusjogo = new StatusJogo(jogo.getController());
        atualizar();
    }

    private void ver_se_acabou() {
        if (jogo.getJogadasSemComerPecas() == 20) {
            JOptionPane.showMessageDialog(this, "FIM DE JOGO! \n" + "20 Jogadas sem comer nenhuma peça!");
            criarNovoJogo();
        }

        if (jogo.getGanhador() == 1) {
            JOptionPane.showMessageDialog(this, "FIM DE JOGO! \n" + jogo.getJogadorUm().getNome() + " VENCEU!");
            criarNovoJogo();
        } else if (jogo.getGanhador() == 2) {
            JOptionPane.showMessageDialog(this, "FIM DE JOGO! \n" + jogo.getJogadorDois().getNome() + " VENCEU!");
            criarNovoJogo();
        }
    }

    private void atualizar() {
        tabuleiroGUI.atualizar(jogo);
    }

    private void initComponents() {

        pnlLinhas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlColunas = new javax.swing.JPanel();
        lbl_a = new javax.swing.JLabel();
        lbl_b = new javax.swing.JLabel();
        lbl_c = new javax.swing.JLabel();
        lbl_d = new javax.swing.JLabel();
        lbl_e = new javax.swing.JLabel();
        lbl_f = new javax.swing.JLabel();
        lbl_g = new javax.swing.JLabel();
        lbl_h = new javax.swing.JLabel();
        tabuleiroGUI = new TabuleiroGUI(this);
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuNovo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuStatus = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLinhas.setLayout(new java.awt.GridLayout(8, 1));

        jLabel3.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("7");
        pnlLinhas.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("6");
        pnlLinhas.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("5");
        pnlLinhas.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("4");
        pnlLinhas.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("3");
        pnlLinhas.add(jLabel7);

        jLabel8.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("2");
        pnlLinhas.add(jLabel8);

        jLabel2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("1");
        pnlLinhas.add(jLabel2);

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("0");
        pnlLinhas.add(jLabel1);

        pnlColunas.setLayout(new java.awt.GridLayout(1, 8));

        lbl_a.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_a.setText("0");
        pnlColunas.add(lbl_a);

        lbl_b.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_b.setText("1");
        pnlColunas.add(lbl_b);

        lbl_c.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_c.setText("2");
        pnlColunas.add(lbl_c);

        lbl_d.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_d.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_d.setText("3");
        pnlColunas.add(lbl_d);

        lbl_e.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_e.setText("4");
        pnlColunas.add(lbl_e);

        lbl_f.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_f.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_f.setText("5");
        pnlColunas.add(lbl_f);

        lbl_g.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_g.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_g.setText("6");
        pnlColunas.add(lbl_g);

        lbl_h.setFont(new java.awt.Font("Arimo", 0, 18)); // NOI18N
        lbl_h.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_h.setText("7");
        pnlColunas.add(lbl_h);

        menuArquivo.setText("Jogo");

        menuNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuNovo.setText("Novo");
        menuArquivo.add(menuNovo);
        menuArquivo.add(jSeparator1);

        menuStatus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuStatus.setText("Status");
        menuArquivo.add(menuStatus);
        menuArquivo.add(jSeparator2);

        menuStatus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuStatus.setText("Status");
        menuArquivo.add(menuStatus);
        menuArquivo.add(jSeparator2);

        menuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuSair.setText("Sair");
        menuArquivo.add(menuSair);

        jMenuBar1.add(menuArquivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlLinhas, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pnlColunas, javax.swing.GroupLayout.DEFAULT_SIZE, 576,
                                                Short.MAX_VALUE)
                                        .addComponent(tabuleiroGUI, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pnlLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, 576,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tabuleiroGUI, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlColunas, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        pack();
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lbl_a;
    private javax.swing.JLabel lbl_b;
    private javax.swing.JLabel lbl_c;
    private javax.swing.JLabel lbl_d;
    private javax.swing.JLabel lbl_e;
    private javax.swing.JLabel lbl_f;
    private javax.swing.JLabel lbl_g;
    private javax.swing.JLabel lbl_h;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuItem menuNovo;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JMenuItem menuStatus;
    private javax.swing.JPanel pnlColunas;
    private javax.swing.JPanel pnlLinhas;
    private TabuleiroGUI tabuleiroGUI;
}
