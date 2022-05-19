package org.iqmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeaderDTO {
    private String name;
    private String role = "Performer";

    public HeaderDTO(String name) {
        this.name = name;
    }
}
