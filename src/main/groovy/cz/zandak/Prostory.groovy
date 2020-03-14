package cz.zandak


class Prostory {

    public static void main(String[] args) {
        if (args.length < 3) {
            println "Malo parametru na vstupu. Pozadovano je 'vstupni soubor' 'vystupni soubor'  'pole hledanych vyrazu'"
        }

        println "Procesuju soubor " + args[0];

        def vstup = new File(args[0]);

        def bloky = getBloky(vstup);

        def vystup = new File(args[1])
        if (!vystup.exists()) {
            vystup.createNewFile()
        }

        def hledaneVyrazy = args[2..args.length - 1]
        println "Hledam prostory obsahujici vyrazy: " + hledaneVyrazy;

        bloky.each { blok ->
            hledaneVyrazy.each { vyraz ->
                if (blok.contains(vyraz.trim())) {
                    blok.each { line ->
                        vystup << line + '\n';
                    }
                    vystup << '\n';
                    println "Prostor zapsan";
                }
            }
        }
    }


    static def getBloky(def inputFile) {
        def blok = []
        def result = []
        def isprostor = false;
        inputFile.eachLine { line ->
            if (line.trim().startsWith("*")) {
                return;
            }
            if (line.toUpperCase().startsWith('AC')) {
                isprostor = true;
                blok << line.trim();
                return;
            }

            if (line.trim() == '' && isprostor) {
                result << blok;
                blok = [];
                isprostor = false;
            }
            if (isprostor) {
                blok << line.trim();
            }
        }
        return result;
    }

}