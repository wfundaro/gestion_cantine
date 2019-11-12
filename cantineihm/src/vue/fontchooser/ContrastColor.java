/*
 This file is part of JFontChooser v0.1

 JFontChooser is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 JFontChooser is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with JFontChooser.  If not, see <http://www.gnu.org/licenses/>.
 */
package vue.fontchooser;

import java.awt.Color;

/**
 *
 * @author deepak
 */
/**
 * this class provides a static method to calculate the contrast color for a
 * given color
 */
public class ContrastColor {

    /**
     * method to get the contrast color (black or white) based on the inputted
     * color value
     *
     * @param color the color whoes contrast color is to be found
     * @return the contrast color which may be Color.BLACK or Color.WHITE
     */
    public static Color getContrastColor(Color color) {
        // Counting the perceptive luminance - human eye favors green color... 
        double a = 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;

        if (a < 0.5) {
            return Color.BLACK; // for bright colors - black contrast color
        } else {
            return Color.WHITE; // for dark colors - white contrast color
        }
    }
}
