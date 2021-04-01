package io.assignment.kalah.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.assignment.kalah.service.KalahGameService;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Model class for response of service {@link KalahGameService}.
 * 
 * @author Richa
 *
 */
@Getter
@NoArgsConstructor
public class KalahGameDTO {

    private String id;
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<Integer, String> status;

    public KalahGameDTO(final String id, final String url) {
        this(id, url, null);
    }

    public KalahGameDTO(final String id, final String url, final Map<Integer, String> status) {
        this.id = id;
        this.url = url;
        this.status = status;
    }

}
