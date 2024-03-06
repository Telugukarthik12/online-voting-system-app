package com.electronicvoting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstituencyWinner {
    private String constituency;
    private String candidateName;
    private String party;
    private int votes;

    // Constructor, getters, and setters
}
