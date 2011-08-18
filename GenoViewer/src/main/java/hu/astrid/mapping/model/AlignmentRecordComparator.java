/*
 * This file is part of GenoViewer.
 *
 * GenoViewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GenoViewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GenoViewer.  If not, see <http://www.gnu.org/licenses/>.
 */

package hu.astrid.mapping.model;

import hu.astrid.mapping.model.AlignmentRecord;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares alignment records by their mapping position.
 */
public class AlignmentRecordComparator implements Comparator<AlignmentRecord>, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(AlignmentRecord record1, AlignmentRecord record2) {
		if (record1.getPosition() < record2.getPosition()) {
			return -1;
		} else if (record1.getPosition() > record2.getPosition()) {
			return 1;
		}
		return 0;
	}

}
