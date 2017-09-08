/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esignerclientjava;

import esignerclientjava.beans.SignFileBean;
import esignerclientjava.forms.MainForm;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author cihan
 */
public class ESignerClientJava {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        //args = new String[]{"C:\\Documents and Settings\\cihan\\Belgelerim\\İndirilenler\\deneme.tobesign"};//FIXME : remove that for live.
        SignFileBean signFileBean = new SignFileBean();
        if (args != null && args.length > 0) {
            String xmlFile = args[0];
            File f = new File(xmlFile);
            if (f.exists() && !f.isDirectory() && f.canRead()) {
                readValuesFromXmlFile(xmlFile, signFileBean);
                if (signFileBean.getDocumentId() != null) {
                    showMainForm(signFileBean);
                }
            } else {
                JOptionPane.showMessageDialog(null, "file is not found or can't readeble.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "file is not found or can't readeble.");
        }
    }

    static void readValuesFromXmlFile(String xmlFile, SignFileBean signFileBean) throws ParserConfigurationException, SAXException, IOException {

        File fXmlFile = new File(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("DocumentData");
        //printNote(nList);

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                if (nNode.hasChildNodes()) {
                    NodeList nListChilds = nNode.getChildNodes();
                    for (int j = 0; j < nListChilds.getLength(); j++) {
                        Node nNodeChild = nListChilds.item(j);
                        if ("DocumentId".equals(nNodeChild.getNodeName())) {
                            signFileBean.setDocumentId(nNodeChild.getTextContent());
                        } else if ("FileName".equals(nNodeChild.getNodeName())) {
                            signFileBean.setFileName(nNodeChild.getTextContent());
                        } else if ("FileHash".equals(nNodeChild.getNodeName())) {
                            signFileBean.setFileHash(nNodeChild.getTextContent());
                        } else if ("SessionId".equals(nNodeChild.getNodeName())) {
                            signFileBean.setSessionID(nNodeChild.getTextContent());
                        } else if ("UserName".equals(nNodeChild.getNodeName())) {
                            signFileBean.setUserName(nNodeChild.getTextContent());
                        }
                    }
                }

            }

        }
    }

    static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {

                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());

                    }

                }

                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }
        }
    }

    static void showMainForm(SignFileBean signFileBean) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm dialog = new MainForm(new javax.swing.JFrame(), true, signFileBean);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
