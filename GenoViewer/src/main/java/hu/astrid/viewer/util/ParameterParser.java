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

package hu.astrid.viewer.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for parsing arguments in GenoViewer. See {@link Parameter Parameter} for available arguments. For parsed parameters call
 * {@link ParameterParser#getMapIfCmdLineIsValid(java.lang.String[]) getMapIfCmdLineIsValid(java.lang.String[])} with arguments from the command line. If parsing come up against any error,
 * {@link GenoViewerParameterException GenoViewerParameterException} will be thrown. If you want to check if there are any conflicting parameters,
 * use method {@link ParameterParser#checkConflicts(java.util.Map) checkConflicts(java.util.Map)} with parameter map generated by this class.
 * @author onagy
 */
public class ParameterParser {

	private ParameterParser() {
	}

	/**
	 * 
	 * @param arguments the arguments to be checked, use the parameters used in main function
	 * @return the map containing used parameters and associated values (if any, in the case of command line switch the value points to null)
	 * @throws GenoViewerParameterException if there were any error (invalid parameter, malformed values, etc...) processing paramete
	 */
	public static Map<Parameter, String> getMapIfCmdLineIsValid(String[] arguments) throws GenoViewerParameterException {

		Map<Parameter, String> keyValueMap = new HashMap<Parameter, String>();

		int processedArgument = 0;

		while (true) {

			if (processedArgument >= arguments.length) {
				break;
			}

			String preparedKeyCandidate = arguments[processedArgument++].trim().toLowerCase();

			Parameter paramKey = checkAndReturnParameterIfExists(preparedKeyCandidate);

			if (paramKey != null) {

				if (paramKey.onlyCmdLineSwitch()) {
					keyValueMap.put(paramKey, null);
				} else {

					String preparedValueCandidate = arguments[processedArgument++].trim();

					if (paramKey.isApplicableParameterValue(preparedValueCandidate)) {
						keyValueMap.put(paramKey, preparedValueCandidate);
					} else {
						throw new GenoViewerParameterException("Parameter value [" + preparedValueCandidate + "] cannot be interpreted as valid parameter for " + paramKey);
					}
				}
			} else {
				throw new GenoViewerParameterException("There is no such parameter like [" + preparedKeyCandidate + "]");
			}
		}

		return keyValueMap;
	}

	private static Parameter checkAndReturnParameterIfExists(String argument) {

		Parameter retParameter = null;

		for (Parameter parameter : Parameter.values()) {

			Parameter parameterCandidate = parameter.canInterpretParameter(argument);

			if (parameterCandidate != null) {

				retParameter = parameterCandidate;
				break;
			}
		}

		return retParameter;
	}

	/**
	 * @return GenoViewer help message
	 */
	public static String generateUsageMessage() {

		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("Proper usage:\n");

		for (Parameter parameter : Parameter.values()) {
			strBuffer.append("\t" + parameter.getFullDescription() + "\n");
		}

		return strBuffer.toString();
	}

	/**
	 * Data holder class for storing conflicting parameters
	 * Usage restricted to this package
	 */
	private static class ParameterConflict {

		private final Parameter conflictingParam1;
		private final Parameter conflictingParam2;
		private final String cause;

		public ParameterConflict(Parameter conflictingParam1, Parameter conflictingParam2, String cause) {
			this.conflictingParam1 = conflictingParam1;
			this.conflictingParam2 = conflictingParam2;
			this.cause = cause;
		}

		String getConflictCause() {

			StringBuffer strBuffer = new StringBuffer();

			if (conflictingParam1 != null && conflictingParam2 != null) {
				strBuffer.append(conflictingParam1 + " cannot be used with " + conflictingParam2 + ". Cause:" + cause + "\n");
			} else if (conflictingParam2 == null) {
				strBuffer.append(conflictingParam1 + " Cause:" + cause);
			}

			return strBuffer.toString();
		}
	}

	/**
	 * 
	 *  param map the parameter map to be checked, this method is responsible to check existing conflicts. Conflict rules defined inside this method,
	 *  so if you add more parameters, define conflict rules here (if needed).
	 * @param map map containing {@link Parameter parameters} and associated values (if any, command line swithch has null value associated with it)
	 * @return empty List if there were no conflict errors, or list of errors (checks all conflicting situations, not only just the first)
	 */
	public static List<String> checkConflicts(Map<Parameter, String> map) {

		List<String> conflicts = new LinkedList<String>();

		if (map.containsKey(Parameter.HELP) && map.keySet().size() > 1) {

			conflicts.add(new ParameterConflict(Parameter.HELP, null, "Only intended to be used as the only parameter, and there were other parameters").getConflictCause());
		}

		if (map.containsKey(Parameter.SAMPLE) && map.containsKey(Parameter.WORKSPACE)) {

			conflicts.add(new ParameterConflict(Parameter.SAMPLE, Parameter.WORKSPACE, "Sample and workspace defines a workspace to open, choose only one of them").getConflictCause());
		}

		return conflicts;
	}
}

