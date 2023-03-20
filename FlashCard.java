public class FlashCard {
    private String question, answer;

    public FlashCard(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getQuestion(){
        return this.question;
    }
    public String getAnswer(){
        return this.answer;
    }
}
