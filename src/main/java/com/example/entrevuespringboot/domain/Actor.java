package com.example.entrevuespringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Actor entity
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description=" All details of Actor")
@Entity
@Table(name = "actor")
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @ApiModelProperty(notes="Name")
    @Column(name = "name")
    private String name;

    @NotNull
    @ApiModelProperty(notes="Actor Last name")
    @Column(name = "lastName")
    private String lastName;

    @ManyToMany(mappedBy = "acteurs")
    @JsonIgnoreProperties(value = { "acteurs" }, allowSetters = true)
    private List<Film> films = new ArrayList<>();

}
