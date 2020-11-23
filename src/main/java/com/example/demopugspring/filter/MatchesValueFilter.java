package com.example.demopugspring.filter;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Primitive;
import ca.uhn.hl7v2.util.Terser;

public class MatchesValueFilter implements Filter {

    private static final String PARSE_TOKEN = "=";
    private static final String NOT_OPERATOR = "!";
    private String valueToCheck;
    private boolean negativize;

    public MatchesValueFilter(String value, Terser tmp) throws HL7Exception {

        this.valueToCheck = parseValue(value, tmp);
        negativize = value.contains(NOT_OPERATOR);
        this.valueToCheck = valueToCheck.replace(NOT_OPERATOR, "");
	}

    private String parseValue(String value, Terser tmp) throws HL7Exception {
        String fieldValue;
        String pathToBeReplace;

        int firstIndex = value.indexOf(PARSE_TOKEN);
        int lastIndex = value.lastIndexOf(PARSE_TOKEN);

        if (firstIndex < lastIndex) {
            pathToBeReplace = value.substring(firstIndex + 1, lastIndex);
            fieldValue = tmp.get(pathToBeReplace);
            if (fieldValue == null) {
                fieldValue = "";
            }
            value = value.replaceAll(PARSE_TOKEN + pathToBeReplace + PARSE_TOKEN, fieldValue);
        }
        return value;
    }

	@Override
	public boolean doFilter(Primitive primitive) {
		boolean primitiveHasCondition = false;
		if (primitive.getValue() == null) {
            primitiveHasCondition = valueToCheck.isBlank();
		} else {
			primitiveHasCondition = primitive.getValue().matches(valueToCheck);
		}
        return primitiveHasCondition ^ negativize;
	}

}
