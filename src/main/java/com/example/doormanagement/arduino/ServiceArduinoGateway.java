package com.example.doormanagement.arduino;

import com.example.doormanagement.passage.ServicePassage;
import com.example.doormanagement.passage.dto.RequestSavePassageDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author Samuel Molcan
 */
@Service
@RequiredArgsConstructor
public class ServiceArduinoGateway {

    private static final Pattern SERIAL_STRING_PATTERN = Pattern.compile("[\\w]+\\s[\\w]+");

    private final ServicePassage servicePassage;

    /**
     * Arduino writes to serial port only of some passage is performed
     * Will receive string in format 'CHIP_SERIAL_NUMBER GATEWAY_CODE'
     * chip serial number and gateway code are the only ones required for passage
     *
     * @param serialString  serial string input from arduino
     * @return              if passage is allowed or not
     */
    public boolean processArduinoPassageRequest(String serialString) {
        // input format does not match
        if (StringUtils.isBlank(serialString) || !SERIAL_STRING_PATTERN.matcher(serialString).find())
            return false;

        final int delimiterIndex = serialString.indexOf(' ');
        return this.servicePassage.savePassage(
                new RequestSavePassageDto(
                        serialString.substring(0, delimiterIndex),
                        serialString.substring(delimiterIndex + 1)
                )
        );
    }

}
