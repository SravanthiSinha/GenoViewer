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

/**
 * This enumeration represents the possible values of the SO (sort order) tag
 * of the HD (header) record in mapping file headers. It tells how the
 * alignment records are sorted.
 */
public enum SortOrder {

    /**
     * This value means that there the alignment records are not sorted.
     */
    UNSORTED,
    /**
     * This value means that there the alignment records are sorted by query name.
     */
    QUERY_NAME,
    /**
     * This value means that there the alignment records are sorted by coordinate.
     */
    COORDINATE;
}