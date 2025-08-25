package hangman;



public interface Hangman {

    void kelimeatma();
}

class Cityname implements Hangman {

    public void kelimeatma() {
        String[] answers = {"ankara", "istanbul", "bodrum", "mardin", "izmir", "sakarya"};
        int random = (int) (Math.random() * answers.length);
        HangManExercise.answer = answers[random];
        System.out.println("This word is a city in Turkey");
    }
}

class Animalname implements Hangman {

    public void kelimeatma() {
        String[] answers = {"aslan", "kedi", "kaplan", "kirpi"};
        int random = (int) (Math.random() * answers.length);
        HangManExercise.answer = answers[random];
        System.out.println("This word is an animal name");
    }
}

class Fruitname implements Hangman {

    public void kelimeatma() {
        String[] answers = {"elma", "armut", "muz", "çilek"};
        int random = (int) (Math.random() * answers.length);
        HangManExercise.answer = answers[random];
        System.out.println("This word is a fruit");
    }
}

class ForeignCityName implements Hangman {

    public void kelimeatma() {
        String[] answers = {"los angeles", "new york", "chicago", "dallas"};
        int random = (int) (Math.random() * answers.length);
        HangManExercise.answer = answers[random];
        System.out.println("This word is a city in the USA");
    }
}

class CountryName implements Hangman {

    public void kelimeatma() {
        String[] answers = {"Türkiye", "Fransa", "İtalya", "Amerika Birleşik Devletleri"};
        int random = (int) (Math.random() * answers.length);
        HangManExercise.answer = answers[random];
        System.out.println("This word is a country in the world");
    }
}
