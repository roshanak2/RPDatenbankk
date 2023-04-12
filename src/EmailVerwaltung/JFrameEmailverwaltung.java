package EmailVerwaltung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JFrameEmailverwaltung {
    private EmailKontaktDao emailDao;
    private JPanel panel;
    private JTextField idtextField1;
    private JButton suchenbutton;
    private JTextField vornametextField2;
    private JTextField nachnametextField1;
    private JTextField emailtextField2;
    private JButton button1;
    private JButton priviusbutton;
    private JButton neuButton;
    private JButton sichernButton;
    private JButton löschenButton;
    private JButton nextbutton;
    private JButton button7;
    private JPanel labelPanel;


    public JFrameEmailverwaltung() throws SQLException, ClassNotFoundException {
        this.emailDao = new EmailKontaktDao();


        suchenbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmailKontakt eKontakt = null;
                try {
                    eKontakt = emailDao.select(Integer.parseInt(idtextField1.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                emailtextField2.setText(String.valueOf(eKontakt.getEmail()));
            }

        });
        nextbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nextbutton.isEnabled()){
                        EmailKontakt eKontakt = emailDao.next(emailDao.select(Integer.parseInt(idtextField1.getText())));
                        idtextField1.setText(String.valueOf(eKontakt.getId()));
                        vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                        nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                        emailtextField2.setText(String.valueOf(eKontakt.getEmail()));

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (button1.isEnabled()){
                        EmailKontakt eKontakt = emailDao.first(emailDao.select(Integer.parseInt(idtextField1.getText())));
                        idtextField1.setText(String.valueOf(eKontakt.getId()));
                        vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                        nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                        emailtextField2.setText(String.valueOf(eKontakt.getEmail()));

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        priviusbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (priviusbutton.isEnabled()){
                        EmailKontakt eKontakt = emailDao.previous(emailDao.select(Integer.parseInt(idtextField1.getText())));
                        idtextField1.setText(String.valueOf(eKontakt.getId()));
                        vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                        nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                        emailtextField2.setText(String.valueOf(eKontakt.getEmail()));

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (button7.isEnabled()){
                        EmailKontakt eKontakt = emailDao.last(emailDao.select(Integer.parseInt(idtextField1.getText())));
                        idtextField1.setText(String.valueOf(eKontakt.getId()));
                        vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                        nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                        emailtextField2.setText(String.valueOf(eKontakt.getEmail()));

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
        löschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (löschenButton.isEnabled()){
                        EmailKontakt eKontakt = emailDao.delete(emailDao.select(Integer.parseInt(idtextField1.getText())));
                        idtextField1.setText(String.valueOf(eKontakt.getId()));
                       vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                        nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                        emailtextField2.setText(String.valueOf(eKontakt.getEmail()));

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        neuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EmailKontakt eKontakt = new EmailKontakt();
                    //eKontakt.setId(Integer.parseInt(textFieldID.getText()));
                    //eKontakt.setId(emailDao.insert(String.valueOf(eKontakt.getId())));
                    eKontakt.setVorname(vornametextField2.getText());
                    eKontakt.setNachname(nachnametextField1.getText());
                    eKontakt.setEmail(emailtextField2.getText());
                    emailDao.insert(eKontakt);
                    idtextField1.setText(String.valueOf(eKontakt.getId()));
                    vornametextField2.setText(String.valueOf(eKontakt.getVorname()));
                    nachnametextField1.setText(String.valueOf(eKontakt.getNachname()));
                    emailtextField2.setText(String.valueOf(eKontakt.getEmail()));

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }});

        sichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    EmailKontakt eKontakt = new EmailKontakt();
                    eKontakt.setId(Integer.valueOf(idtextField1.getText()));
                    eKontakt.setVorname(String.valueOf(vornametextField2.getText()));
                    eKontakt.setNachname(String.valueOf(nachnametextField1.getText()));
                    eKontakt.setEmail(String.valueOf(emailtextField2.getText()));
                    emailDao.update(eKontakt);


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });



    }

    public JPanel getPanel() {
        return panel;
    }

}

