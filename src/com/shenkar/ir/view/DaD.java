package com.shenkar.ir.view;

import javax.swing.JLabel;

public class DaD {


    public static void main( String[] args )
    {
        javax.swing.JFrame frame = new javax.swing.JFrame( "FileDrop" );
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        frame.getContentPane().add( 
            new javax.swing.JScrollPane( text ), 
            java.awt.BorderLayout.CENTER );
        
        new FileDrop( System.out, text, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   text.append( files[i].getCanonicalPath() + "\n" );
                    }   
                    catch( java.io.IOException e ) {}
                }   
            }   
        }); 

        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }  



}
