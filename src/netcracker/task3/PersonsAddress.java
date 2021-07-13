package netcracker.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PersonsAddress {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Address adr;

    public PersonsAddress(String firstName, String lastName, Date birthDate, Address adr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.adr = adr;
    }

    public static class Address{
        private String street;
        private int house;

        public Address(String street, int house){
            this.street = street;
            this.house = house;
        }

        public String getStreet(){
            return street;
        }

        public boolean equals(Address adr2){
            return street.equals(adr2.street) && house == adr2.house;
        }
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getAdr() {
        return adr;
    }

    public String getPerson(){
        return "First Name: "+firstName+" Last Name: "+lastName+" Birth Date: "+birthDate;
    }

    public PersonsAddress personWithLastName(PersonsAddress[] people, String lastName){
        for(PersonsAddress person : people){
            if (person.getLastName().equals(lastName)){
                return person;
            }
        }
        return null;
    }

    public PersonsAddress personWithAddress(PersonsAddress[] people, Address adr2){
        for(PersonsAddress person : people){
            if (person.getAdr().equals(adr2)){
                return person;
            }
        }
        return null;
    }

    public PersonsAddress[] peopleWithDates (PersonsAddress[] people, Date beg, Date end){
        PersonsAddress[] peopleRes = new PersonsAddress[1];
        PersonsAddress[] peopleResSwap;
        int i = 1;
        for(PersonsAddress person : people){
            if (person.getBirthDate().after(beg) && person.getBirthDate().before(end)){
                i++;
                peopleResSwap = peopleRes;
                peopleRes = new PersonsAddress[i];
                peopleRes[i] = person;
                if (i >= 0) System.arraycopy(peopleResSwap, 0, peopleRes, 0, i);

            }
        }
        if (i>1){
            return peopleRes;
        }
        return null;
    }

    public PersonsAddress personOldest(PersonsAddress[] people){
        PersonsAddress oldest = people[0];
        for(PersonsAddress person : people){
            if (oldest.getBirthDate().after(person.getBirthDate())){
                oldest = person;
            }
        }
        return oldest;
    }

    public PersonsAddress personYoungest(PersonsAddress[] people){
        PersonsAddress youngest = people[0];
        for(PersonsAddress person : people){
            if (youngest.getBirthDate().before(person.getBirthDate())){
                youngest = person;
            }
        }
        return youngest;
    }

    public void peopleWithOneStreet (PersonsAddress[] people){
        String[] streets = new String[people.length];
        int i = 0, j = 1;
        for(PersonsAddress person : people){
            streets[i] = person.getAdr().getStreet();
            i++;
        }
       // String[] streets2 = streets;
        Arrays.sort(streets);
        for(i = 0; i < people.length-1; ++i){
            if (!streets[i].equals(streets[i + 1])){
                System.out.println(streets[i]+": ");
                for(j = 0; j < people.length; ++j){
                    if (streets.equals(people[j].getAdr().getStreet())){
                        System.out.println(people[j].getPerson()+", ");
                    }
                }
            }
        }
      /*  PersonsAddress[][] peopleRes = new PersonsAddress[j][];
        j = 0;
        int k = 1;
        for(i = 0; i < people.length-1; ++i){
            if (!streets[i].equals(streets[i + 1])){

                j++;
                k = 1;
            }
            k++;
        }*/
    }
    public static void main(String[] args){
        PersonsAddress[] peop = new PersonsAddress[]{new PersonsAddress("Annah", "Muller", new Date(1212121212121L), new Address("Alexander strasse", 23)), new PersonsAddress("Sarah", "Schulz", new Date(77712121212L), new Address("Alexander strasse", 53))};
        System.out.println(peop[0].personOldest(peop).getPerson());
        System.out.println(peop[0].personYoungest(peop).getPerson());
        System.out.println(peop[0].personWithLastName(peop, "Schulz").getPerson());
    }

}
