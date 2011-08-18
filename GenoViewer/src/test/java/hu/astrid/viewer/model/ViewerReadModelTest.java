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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.astrid.viewer.model;

import hu.astrid.mapping.exception.IndexFileFormatException;
import hu.astrid.mapping.exception.MappingFileFormatException;
import hu.astrid.viewer.Viewer;
import hu.astrid.viewer.model.mutation.Mutation;
import hu.astrid.viewer.model.mutation.MutationTableFilter;
import hu.astrid.viewer.model.mutation.MutationType;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Szuni
 */
public class ViewerReadModelTest {

	@BeforeClass
	public static void setupClass() {
	}

	@Test
	public void testGetSequenceByReads1() throws MappingFileFormatException, IOException, InterruptedException {
		String expectedSequence = "NATATATAATTTAATAAATACATTCCGACGATACTGCCTCTATGGCTTAGTGGTACAGCATCGCACTTGTAATGCGAAGATCCTTGGTTCGATTCCGAGTGGAGGCATATACATTATATTATATTCTTTTTCATGCGGAAAAAAGATTTCAAATTTTTGGGTATGATATTAATATGACTGTAACGTTAATAGCAAAGTGAGTGTTAATAATGATAAAATAGCAGCAAAATCTCTTTTCCGAGTAAGACGTTTTCCAGTCTAAATTTGGAGTCTGCAGTTGTTTCGCAATTCTTAATGTATGGTTATACTAAATACAAACTTTAAAGCTCTGATTTATGTTTGCAATAAACTAAAATAAAAGCACAAAAACCTTTACCCATTAATTTCAAACAACTTATAAACTACCGGTAAACTTTTTTTCTAACCTTTATAATTTATAAACTAGAATGTTTAATGTCTACGGCCATACCTAGGCGAAAACACCAGTTCCCGTCCGATCACTGCAGTTAAGCGTCTGAGGGCCTCGTTAGTACTATGGTTGGAGACAACATGGGAATCCGGGGTGCTGTAGGCTATTTTTTTATATCCGTCTTTCTTACTACTTGCCTAACAAGTCATGATGTACTCTCAAAATATGTTTGCATGCCTTGTAATATTGGTTATGGATAGCTCCTTCTGGACTTGATCTTTTGTAGCCAAGAACAATGGGTATAGACTCTGACCTTGTGATGTTGTAGCCACAGATTATAATAGGTATTTTCAAGTACAGTAACAAAAATCTTCTAGTTTTTTTTTAGAAAGGATACACCAAGTATAAGCAAATTCAGGAATTGTTGATTAAACTGTCAACTTCGGTAAAACTTTGGGCATAAGTAGTGTGGGAGCAAGTTTAACTAAAATTCTATTCAGATGTCGAATCCAAACCGCTAATTTTGCTCAACTAGCTTTTCATAAAAACCAATTCATAGTTTCATACTAATAAAGACGATTGTTTACTTTAAAACATACGTCGTAAGAACATATATTGCTTTATCGAAAGATAACAAATGTTAAGCTATTATATTATTTAACTATAGCGCAGATTTCGCTTCCTTTACTTAAAAAAGACATGTGACTTGTAGAAGCTTGGAGTGAATACGCAAAGGTACCTACTTAGACATTCGCGTCTCTCTTAGCTGTCAACATCAACAAACTGGCCCCGTATTGAACAGTATCTTACTTGTCGAAGGATTTGACTAAGAAAATTTTATTTCTTTATAGCAATATTCCGTTTTCGCTTAGAAGATTCTAGTCAATTGCCCTATTCTACTTACGCTTTACAGTAGTATCAGAAGACCTGAGTGGGATTTTGCTGCTAGTAGAGGCCATTCAAGTTAACTCCGTTTTGCAACATTTTAAAAGTTTTTGAATTGAATATAAATATCAATTGTTTGATTCCTTTTAGGATTTAATCTTTTCTTTTTATTTTTGTTTCGATTGAATCTTGGATTCCTGTCTATCATGTTTTGGTGGAAAAGTGCTACTAAATTCACATTCTCAAAGCGTGGACCGTGTGTCTTTCGCTATTTGAGTACTCTTGAAGGAACAACTGTGAGGCCTAAAAAAAATAAATTTTTAGTTGGATTGCTTTCTGCCGTTCCAATTGTCACGTTTGCTTTAGGAACTTGGCAGGTAAAGCGACGAGAATGGAAAATGGGTATCATCAATACACTCACGGAAAGGCTTCAACAGCCCGCAATTTTATTACCGAAAACTGTTACGTACGTTAAGTTAACATATACACAAATTGCACGTTTTGCAATTGAACTGTCGTTTTTTACATTAAGCTAACATAATCACTTAGAGAGCAAGATACAAAAAAACTTGAGTGGACTAGGGTTTTGCTTCGTGGTGTGTTTTGTCACGACCAAGAAATGTTGGTCGGTCCAAGAACGAAGGAAGGCCAACCTGGCTATCACGTAGTAACCCCATTTATTTTAGACGATGGGCGTCGAATTTTAGTCAACAGAGGATGGATTGCTCGATCATTTGCTGAACAGTCTTCTCGAGATCCTAGTTCTTTACCTAAAGGTCCAGTGGTCATTGAAGGTCTTTTGAGACAACATACTGATAAGCCAAGATTTATGATGAAGAATGAGCCTGAAAAAAATTCTTTTTACTTCTTAAATGTTCGTGAGTTTGCACAATTGAAAGGAACTCTCCCCATTTTGATAACAGAACTACAACCATCGCTTACACCGTTGCAAGAAGCCGATCATGTTAAGAGAGGCTTGCCTCTTGGTCATCCTCTAAAAGTTGAAATTTTCAACAGTCATACAGAATATATTATCACTTGGTATTCTCTAAGTGTGGTATCAGCTATAATGCTTTACGTCTATTTTAAGAGAGGTTCAGGCACATCTTCTCTGAATTCTGCATACGAAAGAAGCAAGATTCTAAACAACAAACGATTATAAAAAATTTTCATATTTATAAGTTTCTAAATATTATCTACCTAAAATTTTACAAATTTTGGAAGCTTGCTTACTGCGTCCGTCGTTTGAATGTATGAATCGATCATTCCTTCACCAACTTGTTTTGCAAACTTCGGATCGTAAGGAACCTATTATTAGGAAGTTAGTTCTCATGCCTATAATTTTAATGCTCTATAATCATCACGTACCTGAGTTTCAGATAAATTGCTCAGCCAAGAGTTTGACAACAATTCTGATACATATTTTCTGGCGGCCTTCTTTTTATGCTTCGTAATTCCAGAGATACTTCCTAATTTGTTACTGCTGATGTTTTTAAGAAGTTGGTATTGTCTGGTAAATTCTTTTTTCTTGGTAGCTGACACGTGATATAGGAAGCTGTTTAGACAAGTGATAAGGTCATTTATGATCTATAATTCGGTTAGTTTAGAAATTTTTATATCATTTTTTTTAAAAAAAAAAACCAGTTTGGTAAACTTACTTCAGTGTACTTTGTCAATTGACTTAAAGGCGTGGAGTTCTCATAATCGAATGACTCAATTAAATTTTCAATCGTTTCGAACGAGGAATTTTCGTAGTCTCCGTTCTTGACCTTTACCGAAAGTAATCCCAGTTGGATCAATAATTTCATATGAACAATTTCTTCCGAAGTTAATTGCTTCGATAAATCATTGTTTTCGCACAAAACTTCCATTTCCTTAGCACTTAAAACGGCCTTTTCAAAATCGCCGTTTACTATGCTGTCTTGAACGAGAGAATGACCAATGACAGTCAAATGGATCCAAAGCGTGTCCGGTTTAGGCGAATTTCTTAAACTTTCTTCAACTGTTGGCAATTTATCAGATCCATAATCTGCAAAGACCTTTAAATCTCGATTATCTTTCGGAGATGAACATTTGGGAAGATACTGTTTGGGTGGTTTAAAAGCGGTAAGATAATGTATCCTAGCCCGCTCGACCAAAGAAATGCTTTTCCAGGTACTGTGGTCTAAACGGGATCGGAAATTACGCATATCTTCGATTTGAGAGTAAGCACCATCTTCATAAGCCATAGAAATCATCTCTGGTGTTTCAAACTCATTCGAACCATATATTTTGAGACTTGAGTTAATATAGTGAGAAGTAACAGAAGATGGATAATAGGTAGTAGCACGAGTCAAGAGATAATGATCAAGGGTATCGTTTTGAATTTGCTTAATGGACATAGTGTCGTAAACTTTAGCAGCAGCAGGGAAGCCTCCATCAAGAAGAAGATATAAACGAATAAGGGGTAGCTTTAAATGAAAGTTATGTTGACTATAAGTAATGCCTTTTTCTAAAAGACAGATTGCGTCAAAAATTAAAGCTTGTTTTTCGGCAGGTTTTAAATCCTTATTCCCCTCCCACATATATATCAATGAATGAACTGCTAATAACAAAGCTTCATAACCGTGCGTAAAGTCAGTGGGTAATAAGCCTTTACTTAAAGACAAACCCTTTTCAAATGCGACGAAGCATCTACGCACGTAATCGACAACCGATTCTGCAGTAAACGACTCAAAGAGCAAAAAATGGATCTTCAGAAGCAAAACCTCAGCATATAATTTATCGACTTTTTGAGACTACGACGAGTTAATATTGAAAGATGAATAAATACTTACTTCATTGGATTCACCTAGATCAGCCAACTTAAATGCATCTAATAACCGGTGTTGTGCATCAACATTCAGCTTCAATAGATAAGGTCTCAAATCTTCAAAAACAATTGGTTTCATATACAGCTTTTTGATATAGCCAAGTAATGCTGATTCGTGTTCTTCAGGAAAAAACCGTGCGCTGGCTTCAATCCAAAGTAAATGAAGATTTCTTTTTGTGCTGCTGGTTGACAACGCTTTCAATATACAATCCTTTAATGGGACTAATCTATGGAGAAGTTAGTAATAATGCAAAAAAAAAAACTTACTTTGAATCATCATTGGAAGCACTATCCAGCAAAGCTTTGCAAACCTTCCAATCAGTATTCCCAGTTTGGAAAAGAGAAAGCGAAAACGTGAATAAAGAATCCCAACGCGCACAGGAAGCAAGCAATTCAAGCTTCCTCAAAAGAAGGTCGGCGTCAGCATCAACAAAGCGATCAGCATCTTGATGTATTAAAGCATCTAACGCTCTGTCCTTGTCTCCCACTAAAAGCAACACGTCTAAATAAAGATGAAACTCCTCACAAGAATCGATATAACCTGTTGGTTTTTCGAAAATGAGTTTAGCAGTTTTTTCAGCTAGTGCTTTCAGCAAACGTTGTTCAACCTCATTTTCCGACTTTTTGGACAAAAGATACAAAGATGAGATAACCCATAAGGTATGTTTCCTAGAAGGAAAGTTCTTTTGCAATTCTACAGCAGCCTGAGGAGTTAGTAAAAAAAAAACAAATTAAGATCTTACCTTTCTTTGGTGAGACAATGATTTGATGCGAATAGATGCTTTGAAGTAAGCTAATAAGTTTTTTTCCTGCTTTCCATAAGTTTGCAAGAACTTTTCCCAAAATACAAACGATTCTTCTCCTTCAAATAGTTAATAACTTAAAAGTAAGAGGGTAAAAACAAGTCCATTGAGAAATAAAACGTTGCACTTGAATCATCAAGCTACGAAAAGAACAAAGATCGTTTTTCTTAAGCAAAGTTTAACCGATCAAATAACTAAAGCCTTTATCTCAAGGAAATAGTAGAATATTCAAAATAAAAAATACGACCAAAGTGCTATCAACATTTATGTTCATCTGTTCAATAGAACCTAATTATACTTACCCTTCTTTTGATCATCATAAACAGCTTGTATAATGTCTAAAAGTTCCAAGCTGTTGATGGGAGTCGATTTTAACGGTTCTAAAAGTGCTAGAGCCTCGGGACCACGTCCTGCCTGAGCCAAGGATAACGCTGAATAAACCTAAAAAGGATGTTAACAGCTAAAAACATAACTCGATTACTTACAATTGTTGATTCTTTACTCCCAGAACGACGCATTGTAGTAGGCAATGAAATTAAAGCCCAAACAGTAGTCAGAAGTTGGTAGGTGTTGCAGTCAAATTATTATTCTACAGAGGAGAATATTATAGCCAGCGTGGTAGAATCTGGATATATATCTACTGCAAAAGTGTAATTGCATTGGTTTAAAGGGTATACTATGGTTAAGTAATATATTCACAGCTGTACAATTTACAGTCATAACTAAAACTTCCTTAAGCCGTAAAGAAATACCTGGTGTTGTAAAATTTGTTGTATATCCACGGCATGGTCATATAATGTGATTTTGTGCTCAAATAAATATAAAATATGCATAATTTTTGTACATTTAATTTGAGAAACCCATCTTTTGTTGAGAGGCTGTCAATGAATAGCAGTTTCATTGAAAAGCAGCGGGATGACCAGAAAAGTATTTTACAATGGCAAGGGAGTAGAAAGCTAGCGTAATATTCAGAAAGCTAGGTAATTGAGCAATCCTTTAATTCATTGCTAAGCATGCTAGGTAAACGCAGTAAACCTTTCAGTTTTCATTTAGGTATAAGGCTGTTTAATGAGTATCTCCACTAAATTTAAAGATCAAAACTCAGTATCAATTCTTAAAAGTTTTATTTTATTTAATAATCATATACTTCTCATAATCTTTCAATTTTTTCCCCATTTTGATGATATTTTTATTAATCCTACAGTAAGCTCTATGATATCGTTATTCTTCAAATAGGCTGGTCAGCACGTGGACGGTGTTACTTATCGTTAAATAAATCGTACTAAGGAGGTGCGATGTAAATGATATGCTTGTCAAGTATTAACTGCTCTCCACCAACCGCCGGTTTAACTGATTATTGTTGAAAAGCGCAGACGAAGTTTAGAGAATTACTAGCGTATTTTAAATTTAATCAACGGACTATTTTTTATTCCTTTGAGATCCGACTTTATCGCTTTGCTTCTAATTTTCCAAAATTCAGTCTATCTACGCGATCCAGCCCTGTTTGCGTAAATTTCATATTATTTTTCTTTAAACGTTTGN";
		ViewerReadModel readModel = new ViewerReadModel();
		readModel.loadSamFile(new File(System.getProperty("viewer.testfiles.dir"), "c1215jo.sam"));
		File generatedSequenceFile = new File(System.getProperty("viewer.testfiles.dir"), "c1215jo_consensus.fasta");
		generatedSequenceFile.delete();

		//Generration
		Assert.assertFalse(generatedSequenceFile.exists());
		String generatedSequence = readModel.getSequenceByReads();
		Assert.assertEquals((long)readModel.getActReferenceLength(), (long)generatedSequence.length());
		Assert.assertEquals(expectedSequence, generatedSequence);

		//Wait for file construction
		Thread.sleep(500);

		//Load from file
		Assert.assertTrue(generatedSequenceFile.exists());
		generatedSequence = readModel.getSequenceByReads();
		Assert.assertEquals((long)readModel.getActReferenceLength(), (long)generatedSequence.length());
		Assert.assertEquals(expectedSequence, generatedSequence);
		
		generatedSequenceFile.delete();
		Assert.assertFalse(generatedSequenceFile.exists());
	}

	@Test
	public void testGetSequenceByReads2() throws MappingFileFormatException, IOException, IndexFileFormatException, InterruptedException {
		String expectedSequence = "NNNNCGAAAATTTTTTAAATAAAATGCATTTTCTAGATACTACCGCGCAATCACTCCCAGGGAGTACGTTCGGAAAAGAAAGAGCGTTTGGTGACAAGTTATACGTACATCTCTTGCGCATTTTTTTAACCCGTAGAGAACGGCAGTACGCGGCCCTGCCAAGCCGGTTCAATTGACCTCTCAGGATAACACTTGACTCCCAATGGTGACCAGGCCCACCACCGCTACGACGTCACATCATACTTCGCCGCGTACCATCCTCGCAACCTGGTCCACTGTTCCTGCACTAAAGCTGGAACTTATTGTCGTGCAGCCCTACGTAGGATGGAATCAATACAAAACTCCCAACCCGGTCCTAGCTTCATATGGAAACCGAAAGCATGCGCTACGGACTTCAACTAACGAACAATGTGTCAACGTGCGCTATTGGGTAGTTCATCGATACCCAGAGGTTCCGTACTCAGCGGGATCCGGCGCGGCCCTCAGGTATCATCCTAAATGACAAATTAAAGCGAGAAGAGATTCGCGGCGAACACGGAAGAGCGCTTCGGAAGGAATACGTATTAAGCACTGATCGGCCCAGTGCAGGCTATCTTCCGATACCTGTCACTTACGGGCGGTTTATCCTCTCGTATTCGCCAATCTGTGGTAGTGAGACATGCAACTACGATTGACCATCCCAGAAGCCGCTGTGCCTAGGCCACGGTACCCGAGAAATTCATCCTCTGACTCTGTTACGTATACGCCAGTATTTAATTCTCTTTGTCCCCCGGCCTGTCCGCATACGGTTAAAGCGACGCGTTACATCTGTGACTAAATTCCACTGGTATAAGGGCCCCTGTTTAGCCTTGCAGCATCCGCACGTCCGCGCTACAACCCGACAGGCAGCGCGTCCAAGCGTCCTTAGAGTTACAGCGGCCACAGCACATGATGGGCGAGCCATTGGATCGGGGGAGGAATCCGGTACTGGACACTATGTGGCCTGACCATAACGGGTCAATCCTCCCATGGGCGGGTGGACGACGCCAATATCGTCGCTTTCCTGTCCCTTCGCCCCCTGACCTATATGCCAAGCCACAAGCAGGTCCCGGTGCTGCAATGATGAACCACTGCAACATAGATGCGAGCATTCGAAGAACTTCGATCCCAATTGTTAATCTTGGCGGGATGTATAGACTACCTGGTTGGCTGACGACGGGACAAACGCGAGTCGGATCGTACCTGTGTTACTTGGGATGCGGCGTGTGCATAGTAACGGGGACGTGGCGTCACTCAGTGTTGTCTTGCGGTCTCAAACTTTTGGGTGACATGGGATAAGCTCGAGTCACTAAGTCCAACATGATCCCAGTAACGGTGCAACTTACCACGTACCGCCAACATATGCTGTGATCGATTCGAAGCTCCAACGGGCGCGTTAGGGGGTTAGCTTGCTCGACGTCCATTCTGAGTACGGGGCGCTTTCTTCTATATCGAGGTCACATGCTCTAACGCGCGCTCAGGTCTATTTCCGAATAGCTGGACGCATTTGATCTACCGTATGGCTACTCGGTTACGGACCCAGCTGCGCATGGGTATAGACATAGCACTCGGCGCTTCGGAGGTCGATCGGAGGAATCTGGGGCAGTTTCGTGGACAGGTACGCTGCCCCACGCTCGGCTGGCGTCCGTACTAGAGAACTAGCTTAAATTAATTTGCGGCATAGAGAAAGCCTGCGTACCAGAGATATGATAGCAAAACGACGTCATGCGTGCTGGTTCGTTCACCGCTAACACACCCGGTCCGCAGGTTGGTAAGGCAGTAACCGGATCGCCGATTAACTCCGGTCCGCCTGCTGCCCTTTTATTGCCCTCGGCGTAGCGAGCTTGCGAGTATCGCGCCATCATGCTTCTTCACTGTCGAAGCTGCCGAAATAAGAGTCCCGAAATTGTTCTTGGTTCGGATGTACGATAGTGTCGTCCCACATATGTTTGAAGGAAGGGAAGGTGCACAGCCTCTTGCTGAATGCTTTTCTTCTCTACTCAGTCGGATTTATGTCTCGTCTCTTACCACCCCGGCTGTGAGAGATCTGAATGGGCCCCCCATTTGTCTCGGAGTATGTACGACGATTATGCCGGAATTAGTCAATCAAGCTGACCTTCTCCCTTTGCGGGAGTGGCAGGGGTAACAGTGCTGAACACTGCGATCAGCGCAAGATCGCTCACACAAAAGGCCCAATTCCGTGAATGCAGTCCGTTCGGCCCACCAACACAGATCTTTGTGAGTTCAGGCAGAATGGGGGTGAAATGTATCGATATAGTGAGCCGCCACCATCTCTTTGGGATCAACATCGCGTAGCCGCATCCGAGCTCTATGGGAAGACCGCCTGCAACCTATATCCCAGCTCAATATCGTTTCTATACCTCGGATCACCCATAGGGAATCAGCTTACCGCAATCCGGTTATCGTTGTTGGATAGACATTAAAACGGTCGGTAAATGTTAACAGTATTCACAAACGTTAATCGCAAGAGCGTGAAGATCACATCGGGGTGGATCTCTCCGTGAAAGTTTGTCGAAGAACGGATTTTTTATGCGTGTATCGGCCGCTACTAATGGTAGCCGCAGTCCACTTGCCCAGCTCCAATACCTTTGTCCACGGTACCAGAGTTCGCGTAAAGGACGGCCTTATCTGTGAGTCTTCTAGTAAGAAGGTTTACCAAGCTAACAACCCGGCATAGTAAAAGAGAACTCAGGAGACTCTTCAATAACCCTCTGATTAGTCTCGGTAAACGAGAGATTCCGCGTGATCTTGAGTTTGTATCTCTACAGCTAATCTTTTGTCGGACTTTCTATTCTATGTCGAGCGTACGTTCGAGTAACAGGACGACCTGAACATCGCAAACCCGATGAAGAGATCTGGTAGAAACACTGGTACACGTTTGTGCACAGTACCTGTCCAGGAAACTCCCTGTACAGGTGAACCTGGCCAGACTTTCGGATAGACCTTGCCATCTTATATGGGTATAGTCTTGGCCACCCTAGAACCTCGGACCTCGCGCGATACGAACTCGATCATCAGCGTGTTAAACTTGAGCCTGCGCTGGGCAAATCCATGGTTCTAGGCCTATCGATAAGATATCATCGCCCACTGACCCCTACGCTTCAAGTATTTACACATATACGAGCAAGGGGAAAACCTTGCGGAAGTCACGTATTCGATGCGTATAGCGACTAAGGTATGAATCATACGTTGCGCGTCGCGACTCAGAGAAAGCGAGTAGGCGTAGGATTAGGTGGGCTCGTTAGCATTCTTCAAAACCTTCCTAATCACATGATGCGATTGTACAGTTGACAGCACCCCATGTCATATGTCAAGGTCGCAGGTCATGTCCCACTTGAGCTGTTACAGCAGGAGATCAGCGTACCCAAAACGGCGTCTGAAATTTGTAGAAGCAGACCAGAAAGCAAATGAAGACGACTGCGGTAACGCTGCGCCCTATGTGCCCTCGGTCACGGTCGACAATATGAAGCATTGAATCTTTTCATCACTCCTGTTCAGCGAGCTTCCCGCTGGGCACTTAATCAGCCTGCGGACTATCCTTATCTAGCTACCTAAAATCGGTTCACCAATGCAATGTCTTGCCGCTATCCCTTATTGAATGACGGCTTGTGGTGCCTAGTAGGCTTCTTGTGGGCCGAATGTAGAGAGTCATCGCTTCATGCTGTGTAGTTGGCACCTTACCCAGCGGGTGGTCTATCTAAGGGGGGCGCAGCCTCAGCCGAAACGACGTAGCCGTCGAACCATATAGTGATATCGAAGATCGCTTAGGCTCCGCCACGACTAGTACCTCGAACTTTTGAAACAAGGTGTGTCATTGGCTTCTAATAGGCGGTAAGTGTCCCGGATATAGAACCAGACACCCGTCGTTTTTCCGCGCGGTAAAGCTCTAGCTTAGAGTCCTGCCAGGGGATTTACTGATGAAACCCCATTAGCAACATGACTCTCGCTCTACTTCGGACCAGTCTAATTCGCGTTAACCAACCTGACGACCACAATGGCGGATAGACCCGGGGTTTAGCCACTGCATAGATCGCATCACCTAGTTGGAAAGGTGTGAGTCGGGGAGATCGCTCGACCAGTTCCGTCTACTGTTACGGGGCTAACCGTTTTCTAAGGAATCTAGTAATAGGACGCGGCCGCAAGTAGGTCTGATGTTAGCGTATACGTATTGTCGGGCGACCACCCAGCGATATATGTAAGGGGTGAAAAGCAAAATGAGAGCACAGATGTGCGACTCCGACGTCGCTTAGCAGAGAATGGCAAGAAAATAGTGGTTAAGACGGCACGAATTATCAACGTCCTGTCGGATTATTCCGACTGTTTCCATGTAGACCATGACTTATCAATACACACCTACTTGTCGAAAGTCTACGCGACGTACTGATTCGGTCATTGAGCCCCGTGGATACCACACCACCCTCAGCTATGGGCTTTGTCGGTCGGGTCTGGAGACTATTCCGCAAGAAATAGGGAAGTATATGACCTCTAGCATCTCGAGTACGCTGTTCAAGGTTAAAAGGTGAAAGCTGGGACGGGTCCAATCGATTGCGGCGATAAAGACTAGGACATCCACGATCTCAAGCGACTGACGCTGCACGTACCCGTCCGCGAACTCAAATGACTCACGCATGATAATCCGCTTAAAGTGCAACAGGCTTGACCATCTACATTTGCAAGCAACACTGACACGTCTTCCGATGAAGATCCTTTGTAGCTGTTCTTGCGGTGCGCGAATTTGAGGACTGCCGGTCCGAACTTCTGGTGATCCTACGGATAGACATGGCAATAGACGCCCCATCCAAATTGTTCCTGCAAATAGCGATAGGCGATACGAGCTTTTTGATGCTTGGCTATCACAATCGAGACCTACCCAGAGGTCCGCTCGGTGACGATTGTGAATACAACTTTTGTGTGTTTCCATCACTGGGGGGGCTGCAGCAGCCGCTTAAGGGGCATAAGCTTGTATTTTCTCTAAGGCGGGTATCATGCCATAAGGTGATAAGCGCACCCCCGCCCTGCCGATACATGGGCAGTTGTCTAATGTGCACTGAAGCGTTTCTATTTTAACGCTGGCTAGTGTAAATGATCGGAGAAGGAGGATGTGCAAACCACCTTGGCCTTCAAGCCCGGGATAAGTATCAGAGGCTAACTGAGGTCTTTGGCCAGAACTGGATATACCGTACCGGCACTGCCAAAAGGCTCTCATCAATGAGAGTTGTGAGTCAATGCTCAATGCTGCGGCGTAGTTCTTGATGTATTCAACAATCGTAATAATGTATCGTACCATGTTGAATCTATATCTTCATCAACGCCAATGCGCCATACACTGACGCAGCGAGCCGCCTTCATACTTTGTTAGCACGGCTCCACACATATGGTCCGACTCTAATGAAGATCCTCATGTTATATCGGCAGTGGGTTGATCAATCCACGTGGATAGATGCCCCCCGAGCCACGAAAACCGGGCACTTGTAACTCCAGGCTCAACTATGCGACGGACGGGGTCTTGTTACCGCCACTTGAGACTGTAAAGAGATATCGTCCCTCTGTGAATCCTCAAGAATTCCAAGCCTAGTCTTGGCGGTGGTTACCGCCTCCTTATACTCATTATGAAGACGGTGCGGGATCGCCCCTGGTATTTAGGTCTACGCTAACAACCACTTCTTTGGGGACGCCTCGGGGACTGACAGATAATGATCATGGCCCAAGCTATATACGTGTGTCTCGGGGCCTCTCCGTATACGATTTAGGCCGAGGACTTGGAATACGGCTTGGCAACCCCACGGCATGGTATCCAAGGGAAGAGACCGATAAATTACCCGCGCTGTCGAACTGCGATAAGATTTTTTATTCAGCGTCAATACGAATAGCTTATCGGAGGAAGAGTTAAAGTTTGCTAGCGAGTTTTCTTGACTCAGTGGGCGGGGGTACAATATGAGCTCTCCTGTTAATCCAATCTTTAGAAGGAATTTGTGCCCTCTTGTCAAATACCTGTGAGGTCTAACGAATATTGGCACATTGTGGCTGCCTCGCCGGCCGCTTCCTCAGATTAGGTACGCCGGAATCGGTCAAAGTCCTTCGCCTTAGTTCCGCATAACTACTTCAGGGAAGCTACAGTCCCACTTCGGATGGTACAACCCCTTACCTACTCGGATATAAGGAGGGAAAACATCCGAATTTCGACCAGGTCGTCGGGCGGCTTACGGTCCGATGGAGAATTGGCGAGCTGATTTGGGAAAGGAAGTTATCTGACAATAAAGGCCTAGCCAATGTTTCAATTTATTGATCCCGAGGTAAACATCAGCGGCTACATGTACCCTTTGTGCGTAAGATGCATTTAGAAGCCTGAGCTATGCATGAATGAGAGCTTATGAGCGCGGTTTCTTCCACGTGATCTTGAGGCTCATCGGACGCGGGCTCTGCAGCCTTGGGGCAAAGGATGATCCAGACTACGATACTTGTGCTGTTTCTCCAGCGGACACAACTATAACGATCAAACTACGTAAAGATTGAACCCCTCGTTGATAGTGTAGCTCCTCTGAGACCCTCTGTAACATAACCCTTGACGCGAAGTTCGGGTCGGCCATCACAAGTTGCGTATGGCAGGCTTCCCTACGTACTCGGGGTAGGGAGGTCTCATGGAAAGACAATCGAGCGCCACTTACCGGCTGACCGTACTTATTAAAAGATTGATGACAAAGTGATGCGGGAAACTTTGGAGGGATCACTCATGTCACGAAGAAAGTTGTTCATAAACTGACATGCATAACTCGCCACGTTGGGGCTATCCAAGCATGAGCTCGGATGTTTCGGCAACGTTGAAGGGGAATACCGGGCCGCCACTGATCGCCAGGGCTGTAGAATCCTGCAATATGCTTAAACACCTCCATTAGCTGTGCCGAGGCGGGCTCCAGTAAGGCAACAAAAAGTCGATGTCATCTAAGCGTCGGCCGGACCTGGGCGCACCTTGCGTTTTTCAACGAAACTGCCTATGGGTCGTTAGTAGCGACACACTCTAGCGCTATGATGTTTCGCTAGGAATGTCCCAAATTACTAGCGGGTAATCTGAGAGGGCATCACGGTGGGATGTCCGTCGGAGCGTGATGAATGCAACCCTAAAGCCCTGTGCTACTCGCACGAACAGGAGCACCTAACCTGCGAGACTTGCTGATTTCGAGCCCCGGTCGCCCCCGGGGGTGATACTCGTCCATAGTATTAGGCATCCATTAGGGCGATGTTTAGAGCATCCATTAGCGGATAGTTTCATATGTGGGTATTACAGTCTGAATCGAGCACCGCACGATAGACGCTATAGTACTTAGATATTACGACGTACAGTGTAGTCTCTAGGACTCCTCTGATGGCTCCCACTACCCCGCGTCTACACGCACTCTTCTCGCTCCAGGGTACAAATAAATATGTATCTTCGCCCCGGTCGGCGCGGGACTGGACACCCAGGGATAGCTCCGGATCAGCGAGCGCAGGATGCAATATTGGAGTTCTGATGAACAGCTCGGTACGCCGGGTCTAGTGGTTTCACGCTGAGATAGACTACTCCACAAGCTAATCCTTCACGGTCGCCTCCTTACCAACTATCGTTTGCCTATATCAGTGGTCGATGTTACCATAAGTGAGGTCACGGGACACGGACTAAGGTTAAATGAATAGGAAATACGGGAGTCTCTCCAGCCCTTATGGGCCGCCATCGCCTCTGCGTCAGTCCGTCCAACAGCTTTGGAGGCCTTCTGACAGCCGCTGGATGTACTGCTACGCCTACCGGAGTGGTCTCGCGCATACGCACCAACTTTTTCCTTTTGACCAGTCATACATGAACGCAAGACGAATTCGATTGGCCTGGAATCCATTAGTGCTGCTCAGTGTTCTGCCGCGCGCACTGCGAGTGCATGCGAAAGTGCGGCCGTTCGATAGGGCTCTGTGTAAGTTCTCACTGATCTTGTCCGTCCTACACCACATTCTTATAAGTTATGTGTCATCCGAGACGCAGCCCTGGACGGAATACATGTTGTATTTGTCGACGACCGTAATCCGAACGGACTTGACCGAGGTCCTGTGCCCTGCATTTTTCTGCCCTACTTCCATGACTCTTCTATAGCGTAGGCCGCTTGTGGCGCGGCCACTAAAAACGCGTGCCAGCCGTAATTCCACCGGGATGACGCGACTTTACATGCCCCGAAGCGTTATATCCGCGTAGCTTCACTGTGGATAGGGTTTTGATCGCGAAGGAATCTCAATTCTAATCACAATATTATTCCTAAGGCAATACTCGGGCCACGCAATGAAACTTGGCGGGATTATCGTAAATAGGATGAATCGATTTGTGCGAGATAACGCGCTGAAAACTGAAACCATACCCTCTAATACGGTCCATTGATGGGTAGTATTTGTTGACCTGCGATTTGTCACGTTCGGGCCGTGGAATTTTTTTTCGGCGCATTTTATCCCGCGTACCTGGGCGCCGATTTCCTAGCGACTATTTCGCAAGATCCACGTGTTCGTCACAAGGAGATAGCTCCAGCCGTGATGCCACACCGGCACATTTTCTGCACGCGTGAGCCGTATATATCCAGTTAATTTTGGAATCCGTAAGGTCGTCGCTGTCATGACTAGTCGTCAGGGACTCAATCAATCCCCGGTATCTCAAAACTCTGCAGGAGTTCCCAGTTATGGCACCGTTCTCAATTTGACGTAGATGATGCGCGATTACGTACCGTACCCTTGGTGAAAACCAACCTGCTTCGCGCCGTAATGTCAAGCAACGGACAAGCTGCGCTGGGTCGGCAAGACAGTCCGCGCTATTCACCGAGTGAAGCCTTAAGCTCATGCAGTGAAAAGATGCGTCTAGGACGTGTCCATCTCAGAACTAGCTGTGCAGGTGAAGCCCAGCTTTAGGTGTCGTGACGGTCAAGATATCCAGTAAATGCGCCGACTAGTCCGGGCAATACGGTTTGTGATGCTACCTGATCTTGAGGACATTTAGTACACGATTCGGATTGGCCGGGTCGTGGATTATCCTTGTTAAAAACAGGACTCTATCAGATACCAGACAGCGAAAATTAACGTCGGCGCTGAGAACGAGCGTCATCGGGAGCACGCGGCGAAACGCTCCAATTACTAATTTCCTAATATGATATCGCCCGCCACCAGCGGGTAAGAGGCCAGGTATGACTTCGTTATTCGCCATTGACTCAGCGCTTCAAACCGGTTGCTTGGTGAGAGTCAGCATTGGCGTCGTTCTGGGAGCTCCTCCGGTCATATCCTCATGCATCGCTGTACATCTCTATTATCCCTCGGCTTGGGATCCTACTTCGCCTTCGGGAACTTGAGCTGCGGGCCCAGGCAATGCTGGGGCACATTTACTAGGAGATCGATATTCATGCTGGCCGCGCCATAATTATGTCACTAGACACCATTCAGTTGCACTTTGCTAAAAAGATTTGAGTTACTACTAATCTGTAAGATCATGCCTCAGCCCCGATCACCACGGCACCCTATTGCGCGGATGACCAATCCCATGGTTTGTTTCGAACCAACTATCAAACCTAAGCCAAAATCGAGTTAGCTCCGCCCGGTACGACAATAACCCGAACCAACTTCTAGACCAACCTGACGCGATTCCGTGGTACTGTACGCTCAGAAACACTCCCTCCGGAGAAGCAATCCCCTATTCCACTGCCCATTGTCTACGGGTCCGAATTCATGCGCGGAGAAACCCGGTAGCGACTGTTTGCAGTTAAAGGTGGCGCAAACGACATTAATTATATTAATTCCGAGGCGACGATGTTCTTAGATTCGGTCTATTGCAATACTAAGAGTTCGAAGCAGGCCGTCGCAGGGAACGAACCACCTACTTGATCTACACTTGATTGAGCCAGCATGTAATTGCTCTGAACAGTATCATTACAAACGGCTCTAAACTTCATAATGAGGTAACATCCGNNNN";
		ViewerReadModel readModel = new ViewerReadModel();
		readModel.loadBamFile(new File(System.getProperty("viewer.testfiles.dir")+"/WebstartFiles", "alignment4.bam"));
		readModel.setActAlignmentRefNameIndex(0);
		File generatedSequenceFile = new File(System.getProperty("viewer.testfiles.dir")+"/WebstartFiles", "alignment4_consensus.fasta");
		generatedSequenceFile.delete();

		//Generration
		Assert.assertFalse(generatedSequenceFile.exists());
		String generatedSequence = readModel.getSequenceByReads();
		Assert.assertEquals((long)readModel.getActReferenceLength(), (long)generatedSequence.length());
		Assert.assertEquals(expectedSequence, generatedSequence);

		//Wait for file construction
		Thread.sleep(500);

		//Load from file
		Assert.assertTrue(generatedSequenceFile.exists());
		generatedSequence = readModel.getSequenceByReads();
		Assert.assertEquals((long)readModel.getActReferenceLength(), (long)generatedSequence.length());
		Assert.assertEquals(expectedSequence, generatedSequence);

		generatedSequenceFile.delete();
		Assert.assertFalse(generatedSequenceFile.exists());
	}

	@Test
	public void testSetMutationsFilter() throws MappingFileFormatException, IOException {
		ViewerReadModel readModel = new ViewerReadModel();
		readModel.loadSamFile(new File(System.getProperty("viewer.testfiles.dir"), "c1215jo.sam"));
		EnumMap<MutationType, Integer> map = new EnumMap<MutationType, Integer>(MutationType.class);
		for(MutationType mutationType : MutationType.values()) {
			map.put(mutationType, 0);
		}
		final List<Mutation> mutationList = readModel.getMutationList();
		int fullCoveredMutaions = 0;
		for(Mutation mutation : mutationList) {
			map.put(mutation.getMutationType(), map.get(mutation.getMutationType())+1);
			if(mutation.getCoverage()==1)
				fullCoveredMutaions++;
		}
		int sum = 0;
		for(Integer i : map.values())
			sum+=i;
		Assert.assertEquals(mutationList.size(), sum);

		readModel.setMutationsFilter(new MutationTableFilter(Arrays.asList(MutationType.SNP), 0, 1));
		Assert.assertEquals(readModel.getMutationList().size(), (int)map.get(MutationType.SNP));

		readModel.setMutationsFilter(new MutationTableFilter(Arrays.asList(MutationType.MNP, MutationType.DELETION), 0, 1));
		Assert.assertEquals(readModel.getMutationList().size(), (int)map.get(MutationType.MNP)+map.get(MutationType.DELETION));

		readModel.setMutationsFilter(new MutationTableFilter(Arrays.asList(MutationType.SNP, MutationType.MNP, MutationType.INSERTION, MutationType.DELETION), 1, 1));
		Assert.assertEquals(readModel.getMutationList().size(), fullCoveredMutaions);

		readModel.setMutationsFilter(null);
		Assert.assertEquals(mutationList, readModel.getMutationList());

	}
}
