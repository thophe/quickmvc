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

package view.extension;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * Implements a simple outline border for JComponents
 * @author krm
 *
 */
public class OutlineBorder implements Border {

	private final BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_BEVEL, 1 );

	private Insets insets;

	public OutlineBorder(int top, int left,
			int bottom, int right) {
		insets = new Insets(top, left, bottom, right);
	}

	public OutlineBorder() {
		this(0,0,0,0);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y,
			int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		x += insets.left-1;
		y += insets.top-1;
		width -= insets.right+insets.left-1;
		height -= insets.bottom+insets.top-1;
		g2.setStroke(stroke);
		g2.setColor(new Color(106,106,106));
		g2.drawRect(x, y, width, height);
		g2.setColor(new Color(156,156,156));
		g2.drawLine(x+1, y+height+1, x+width+1, y+height+1);
		g2.drawLine(x+width+1, y+1, x+width+1, y+height+1);

	}


}
