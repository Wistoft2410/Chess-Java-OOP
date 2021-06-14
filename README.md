# Chess-Java-OOP

Jeg har udviklet et program til skakspil. Programmet er lavet ved hjælp af Java og bruger forskellige java-biblioteker og herunder, klasser til den grafiske brugergrænseflade (GUI) såsom interface- klassen ImageObserver.
Projektet har haft en række dårlige omstændigheder, herunder problemer i den oprindelige gruppe, efterfølgende GitHub og autogeneret .idea filer, hvilket har resulteret i at det ikke var alle de opsatte krav


## Display metode i Felt objekt
```java
// Tegner feltet til ui, ved først at tegne farven og dernest navnet på feltet
    public void display(Graphics g) {
        if ((række + søjle) % 2 == 0) {
            g.setColor(new Color(139,69,19)); //mørke felter: g.setColor(new Color(235,235,208));
        }
        else {
            g.setColor(new Color(235,235,208)); //lyse felter: g.setColor(new Color(119,148,85));
        }
        g.fillRect(x(), y(), uiStørrelse, uiStørrelse);

        // Omvendt farve for felt navn
        if ((række + søjle) % 2 != 0) {
            g.setColor(new Color(139,69,19)); //mørke felter: g.setColor(new Color(235,235,208));
        }
        else {
            g.setColor(new Color(235,235,208)); //lyse felter: g.setColor(new Color(119,148,85));
        }
        g.drawString(notation(), x() + 2, y() + 12);
    }
```
## displayFelter metode i objektet Braet (Bræt) 
```java

    public void displayFelter(Graphics g) {
        for(Felt[] række: felter) {
            for(Felt f: række) {
                f.display(g);
            }
        }
    }
```

Læs selve projekt rapporten [her](https://drive.google.com/file/d/1i3fY818P0MPtCcrhnlZUm3pWlTZLAKv0/view?usp=sharing)
