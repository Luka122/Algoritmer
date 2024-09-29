package no.oslomet.cs.algdat;

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi; this.forrige = forrige; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null, null);}
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endring;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra("+fra+") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til("+til+") er større enn antall("+antall+")");
        if (fra > til) throw new IllegalArgumentException("fra("+fra+") er større enn til("+til+") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 1; // Returner hvor mange som er i gruppa deres
    }

    // Oppgave 1

    public DobbeltLenketListe() {
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null!");
        for (T t : a) {
            if (t != null) {      // sjekker om verdien er ikke-null
                if (hode == null) {
                    hode = hale = new Node<>(t); // skaper hode node
                    antall++;
                } else {
                    Node<T> p = new Node<>(t);  // skaper ny node
                    hale.neste = p;                // setter pekeren --> node
                    p.forrige = hale;
                    hale = p;                      // hale = ny node
                    p.neste = null;                // neste peker = neste hale
                    antall++;
                }
            }
        }
    }


    public Liste<T> sub_liste(int fra, int til){
        fraTilKontroll(antall, fra, til);
        Object[] verdier = new Object[til - fra];
        Node<T> k = finnNode(fra);
        for(int i = 0; i < verdier.length; i++) {
            verdier[i] = k.verdi;
            k = k.neste;
        }

        T[] generiskVerdier = (T[]) verdier;
        return new DobbeltLenketListe<>(generiskVerdier);
    }

    private void fraTilKontroll(int antall, int fra, int til) {
        if(fra < 0) {
            throw new IndexOutOfBoundsException("Fra(" + fra + ") er negativ!");
        }

        if(til > antall) {
            throw new IndexOutOfBoundsException("Til(" + til + ") > antall(" + antall + ")");
        }

        if(fra > til) {
            throw new IllegalArgumentException("Fra(" + fra + ") > til(" + til + ") - ugyldig intervall!");
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    // Oppgave 2
    public String toString() {
        if (tom()) {
            return "[]"; // Returnerer tom liste
        } else {
            StringBuilder sb = new StringBuilder("[");
            Node<T> p = hode;

            while (p != null) {
                sb.append(p.verdi);
                if (p.neste != null) {
                    sb.append(", ");
                }
                p = p.neste;
            }

            sb.append("]");
            return sb.toString();
        }
    }

    public String omvendtString() {
        if (tom()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            Node<T> p = hale;

            while (p != null) {
                sb.append(p.verdi);
                if (p.forrige != null) {
                    sb.append(", ");
                }
                p = p.forrige;
            }

            sb.append("]");
            return sb.toString();
        }
    }

    public boolean leggInn(T verdi) {
        if (verdi == null) {
            throw new NullPointerException("ikke tilltat med null verdier");
        }

        if (tom()) {
            hode = hale = new Node<>(verdi);
        } else {
            Node<T> nyNode = new Node<>(verdi);
            hale.neste = nyNode; // setter pekeren fra forrige hale til den nye noden
            nyNode.forrige = hale;
            hale = nyNode;
        }

        antall++;
        endring++;
        return true; // returnerer true for godjkjent innlegg
    }

    // Oppgave 3

    private Node<T> finnNode(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> p;

        if (indeks < antall / 2) {
            p = hode; // Start ved hodet
            for (int i = 0; i < indeks; i++) {
                p = p.neste; // Følg neste-pekere
            }
        } else {
            p = hale; // Start ved halen
            for (int i = antall - 1; i > indeks; i--) {
                p = p.forrige;
            }
        }

        return p;
    }

    @Override
    public T hent(int indeks) {
        Node<T> node = finnNode(indeks); // Finn noden med indeksen
        return node.verdi; // Returner verdien på den gitte posisjonen
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        if (nyverdi == null) {
            throw new NullPointerException("Nye verdien er null!");
        }
        Node<T> node = finnNode(indeks);
        T gammelVerdi = node.verdi;
        node.verdi = nyverdi;
        endring++;
        return gammelVerdi;
    }

    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra, til); // Sjekk at indeksene er gyldige
        if (fra == til) {
            return new DobbeltLenketListe<>();
        } else {
            Node<T> fraNode = finnNode(fra);
            Node<T> tilNode = finnNode(til - 1);
            Object[] verdier = new Object[til - fra];
            Node<T> p = fraNode; // Start ved fraNode

            for (int i = 0; i < verdier.length; i++) {
                verdier[i] = p.verdi;
                p = p.neste;
            }

            T[] generiskeVerdier = (T[]) verdier;
            return new DobbeltLenketListe<>(generiskeVerdier);
        }
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1; // Return -1 hvis verdien er null
        }
        Node<T> p = hode;
        for (int i = 0; i < antall; i++) {
            if (verdi.equals(p.verdi)) {
                return i;
            }
            p = p.neste;
        }
        return -1;
    }
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1; // Returner true hvis indeks til returnerer en indeks (verdien finnes), ellers false
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        if(verdi != null) {
            Node<T> ny_node = new Node<>(verdi);
            if (antall == 0) {              // om tom liste
                hode = hale = ny_node;
                endring++;
                antall++;
            } else if (indeks == 0) {       // først --> node
                hode.forrige = ny_node;
                ny_node.neste = hode;
                hode = ny_node;
                endring++;
                antall++;
            } else if (indeks == antall) {  // sist --> node
                hale.neste = ny_node;
                ny_node.forrige = hale;
                hale = ny_node;
                endring++;
                antall++;
            } else {                        // noder mellom andre
                Node<T> neste = finnNode(indeks);
                Node<T> forrige = neste.forrige;
                forrige.neste = ny_node;
                ny_node.forrige = forrige;
                ny_node.neste = neste;
                neste.forrige = ny_node;
                endring++;
                antall++;
            }
        } else {
            throw new NullPointerException("Null-verdier er ikke tillatt!");
        }
    }
    @Override
    public boolean inneholder5(T verdi) {
        if (indeksTil(verdi) == -1) {       // sjekker om false
            return false;
        } else return true;
    }

    @Override
    public T hent5(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi; // return indesk verdi
    }

    // Oppgave 6
    @Override
    public boolean fjern(T verdi) {
        Node<T> denne = hode;  // lager currnt node så vi kan lettere se node

        if (antall == 0 || verdi == null)  {
            return false;
        }
        for (int i = 0; i < antall ; i++) {
            if (verdi.equals(denne.verdi)) {
                if (i != 0 && i != antall-1) {
                    denne.forrige.neste = denne.neste;
                    denne.neste.forrige = denne.forrige;
                } else if (antall == 1) {
                    hode = hale = null;
                } else if (i == 0) {
                    hode = denne.neste;
                    denne.neste.forrige = null;
                } else {
                    hale = denne.forrige;
                    denne.forrige.neste = null;
                }
                antall--;
                endring++;
                return true;
            }
            denne = denne.neste;
        }

        return false;
    }
    @Override
    public T fjern(int indeks) {
        Node<T> denne = hode;

        if (antall == 0 || indeks > antall - 1 || indeks < 0)  {
            throw new IndexOutOfBoundsException();
        }
        if(indeks < antall/2) {
            Node<T> p = hode;
            for(int i = indeks; i >= 0; i--) {
                if (i == 0) {
                    denne = p;
                } else {
                    p = p.neste;
                }
            }
        } else {
            Node<T> p = hale;
            for(int i = antall - 1; i >= indeks; i--) {
                if(i == indeks) {
                    denne = p;
                }
                p = p.forrige;
            }
        }
        if (indeks != 0 && indeks != antall-1) {
            denne.forrige.neste = denne.neste;
            denne.neste.forrige = denne.forrige;
        } else if (antall == 1) {
            hode = hale = null;
        } else if (indeks == 0) {
            hode = denne.neste;
            denne.neste.forrige = null;
        } else {
            hale = denne.forrige;
            denne.forrige.neste = null;
        }
        antall--;
        endring++;
        return (T) denne.verdi;
    }

    // Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 8
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;
            kanFjerne = false;
            iteratorendringer = endring;
        }

        private DobbeltLenketListeIterator(int indeks){
            this.denne = finnNode(indeks);
            this.kanFjerne = false;
            iteratorendringer = endring;
        }

        @Override
        public boolean hasNext(){ return denne != null; }

        public T next(){
            if(!(iteratorendringer == endring)) {
                throw new ConcurrentModificationException();
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T verdi = denne.verdi;
            denne = denne.neste;
            kanFjerne = true;
            return verdi;
        }


        // Oppgave 9:
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator<T> iterator() {  //for oppg 8
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) { // for oppg 8
        indeksKontroll(indeks, false); //Sjekker indeks
        return new DobbeltLenketListeIterator(indeks);
    }

    // Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
