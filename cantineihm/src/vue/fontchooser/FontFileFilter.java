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

import java.io.File;
import java.io.FilenameFilter;

/**
 * the font file filter
 *
 * @author deepak
 */
public class FontFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".ttf");
    }
}
