package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel implements MouseListener {

    private int turnsPlayed=0;
    private boolean[][] visited = new boolean[3][3];
    private int [][] palyer = new int [3][3];// how choosed button[3][3]
    private JButton[][] buttons = new JButton[3][3];
    private  boolean winingCondition = false;

    public MyPanel() {
        intantiateButton();
        addButtonToPanel();
        setBagroundColor();
        setFocusable();
        addMouseListener();
        setVisitedFalse();
        intantiatePlayer();
        this.setLayout(new GridLayout(3,3,5,5));
        this.setPreferredSize(new Dimension(500,500));
        this.setOpaque(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(winingCondition==true) return;
        int row =0;
        int col =0;
        boolean flag=false;
        for(row=0;row<3;row++){
            for(col=0;col<3;col++) if(buttons[row][col]==e.getSource()) {
                flag=true;
                break;
            }
           if(flag==true) break;
        }
        if(visited[row][col]==true) buttons[row][col].setBackground(Color.red);
        else {
            turnsPlayed++;
            if (turnsPlayed % 2 == 0) {
                buttons[row][col].setForeground(Color.blue);
                buttons[row][col].setFont(new Font("MV Boli", Font.PLAIN, 150));
                buttons[row][col].setText("X");
            }

            else {
                buttons[row][col].setForeground(Color.yellow);
                buttons[row][col].setFont(new Font("MV Boli", Font.PLAIN, 150));
                buttons[row][col].setText("O");
            }
            visited[row][col]=true;

            palyer[row][col]=turnsPlayed%2;
            checkWiningCondition(row, col);
        }
        if(winingCondition==true){
            JOptionPane.showMessageDialog(null,"Palyer "+(turnsPlayed%2+2)+" won the game!!!");

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(winingCondition==true) return;
        int row =0;
        int col =0;
        boolean flag=false;
        for(row=0;row<3;row++){
            for(col=0;col<3;col++) if(buttons[row][col]==e.getSource()) {
                flag=true;
                break;
            }
            if(flag==true) break;
        }
        if(visited[row][col]==true) buttons[row][col].setBackground(Color.red);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(winingCondition==true) return;
        int row =0;
        int col =0;
        boolean flag=false;
        for(row=0;row<3;row++){
            for(col=0;col<3;col++) if(buttons[row][col]==e.getSource()) {
                flag=true;
                break;
            }
            if(flag==true) break;
        }
        buttons[row][col].setBackground(Color.white);
    }

    private void intantiateButton(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) buttons[row][col]= new JButton();
    }

    private void addButtonToPanel(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) this.add(buttons[row][col]);
    }
    private  void setBagroundColor(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) buttons[row][col].setBackground(Color.white);
    }
    private void setFocusable(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) buttons[row][col].setFocusable(false);
    }
    private void addMouseListener(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) buttons[row][col].addMouseListener(this);
    }
    private void setVisitedFalse(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) visited[row][col]= false;
    }
    private void intantiatePlayer(){
        for(int row=0;row<3;row++)
            for(int col=0;col<3;col++) palyer[row][col]= -1;
    }

    private void checkWiningCondition(int row , int col){
        if(upCount(palyer[row][col], row, col)+downCount(palyer[row][col], row, col)-1==3){
            paintUp(row,col);
            paintDown(row,col);
            winingCondition = true;
        }
        if(leftCount(palyer[row][col], row, col)+rightCount(palyer[row][col], row, col)-1==3){
            paintLeft(row,col);
            paintRight(row,col);
            winingCondition = true;
        }
        if(leftDownCount(palyer[row][col], row, col)+upRightCount(palyer[row][col], row, col)-1==3){
            paintLeftDown(row,col);
            paintUpRight(row,col);
            winingCondition = true;
        }
        if(upLeftCount(palyer[row][col], row, col)+downRightCount(palyer[row][col], row, col)-1==3){
            paintUpLeft(row,col);
            paintDownRight(row,col);
            winingCondition = true;
        }

    }

    private int upCount(int key, int row, int col)
    {
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i--) if(palyer[i][j]==key) count++;
        return count;
    }

    private int downCount(int key, int row, int col)
    {
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i++) if(palyer[i][j]==key) count++;
        return count;
    }

    private int leftCount(int key, int row, int col)
    {
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;j--) if(palyer[i][j]==key) count++;
        return count;
    }

    private int rightCount(int key, int row, int col)
    {
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;j++) if(palyer[i][j]==key) count++;
        return count;
    }

    private int leftDownCount(int key, int row, int col){
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i++,j--) if(palyer[i][j]==key) count++;
        return count;
    }

    private int upRightCount(int key, int row, int col){
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i--,j++) if(palyer[i][j]==key) count++;
        return count;
    }

    private int upLeftCount(int key, int row, int col){
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i--,j--) if(palyer[i][j]==key) count++;
        return count;
    }

    private int downRightCount(int key, int row, int col){
        int count=0;
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i++,j++) if(palyer[i][j]==key) count++;
        return count;
    }



    private void paintUp(int row, int col)
    {
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i--) buttons[i][j].setBackground(Color.green);
    }

    private void paintDown(int row, int col)
    {
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i++) buttons[i][j].setBackground(Color.green);
    }

    private void paintLeft(int row, int col)
    {
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;j--) buttons[i][j].setBackground(Color.green);
    }

    private void paintRight(int row, int col)
    {
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;j++) buttons[i][j].setBackground(Color.green);
    }

    private void paintLeftDown(int row, int col){
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i++,j--) buttons[i][j].setBackground(Color.green);
    }

    private void paintUpRight(int row, int col){
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i--,j++) buttons[i][j].setBackground(Color.green);
    }

    private void paintUpLeft(int row, int col){
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i--,j--) buttons[i][j].setBackground(Color.green);
    }

    private void paintDownRight(int row, int col){
        for(int i=row, j=col;i>=0 && i<3 && j>=0 && j<3;i++,j++) buttons[i][j].setBackground(Color.green);
    }
}
