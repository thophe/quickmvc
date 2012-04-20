 /* Copyright 2012 Kristofer Mitchell

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/

package util;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
/**
 * In case you'd like a TextView with no Caret
 * @author krm
 *
 */
public class BlankCaret implements Caret{
    public void install(javax.swing.text.JTextComponent c){}
    public void deinstall(javax.swing.text.JTextComponent c){}
    public void paint(Graphics g){}
    public void addChangeListener(ChangeListener l){}
    public void removeChangeListener(ChangeListener l){}
    public boolean isVisible(){return false;}
    public void setVisible(boolean v){}
    public boolean isSelectionVisible(){return false;}
    public void setSelectionVisible(boolean v){}
    public void setMagicCaretPosition(Point p){}
    public Point getMagicCaretPosition(){return new Point(0,0);}
    public void setBlinkRate(int rate){}
    public int getBlinkRate(){return 10000;}
    public int getDot(){return 0;}
    public int getMark(){return 0;}
    public void setDot(int dot){}
    public void moveDot(int dot){}
};