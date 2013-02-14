package main.models;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.*;

/**
 *
 * @author Jonathon
 */
@Entity
@NamedQueries({
    //create common queries
    @NamedQuery(name = "getAllMenuItems", query = "SELECT mi.name FROM MenuItem mi ORDER BY mi.name"),
    @NamedQuery(name = "getAllItems", query = "SELECT blah FROM MenuItem blah ORDER BY blah.name")
})
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1;
    //main key
    @Id
    @GeneratedValue
    private Long id;
    //name of food item
    @Column(nullable = false)
    private String name;
    //price of the item
    @Column(nullable = false)
    private Float price;
    //description of the item
    @Column(length = 2000)
    private String description;
    //number of sides that go with the item
    @Column(nullable = false)
    private Integer number_of_sides;
    //the genre of item
    @Column(nullable = false)
    private String typeOfFood;

    //required
    public MenuItem() {
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public Integer getNumber_of_sides() {
        return number_of_sides;
    }

    //setters with validation
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.description = description;
    }

    public void setNumber_of_sides(Integer number_of_sides) {
        this.number_of_sides = number_of_sides;
    }

    public void setTypeOfFood(String typeOfFood) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.typeOfFood = typeOfFood;
    }

    //equals and hash
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuItem other = (MenuItem) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    //to string
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        output.append("Menu Item ID Number: ");
        output.append(id);
        output.append("\n");
        output.append("\tName: ");
        output.append(name);
        output.append("\n");
        output.append("\tPrice: ");
        output.append(nf.format(price));
        output.append("\n");
        output.append("\tDescription: ");
        output.append(description);
        output.append("\n");
        output.append("\tNumber Of Sides: ");
        output.append(number_of_sides);
        output.append("\n");
        output.append("\tType Of Food: ");
        output.append(typeOfFood);
        output.append("\n");
        return output.toString();
    }
}
