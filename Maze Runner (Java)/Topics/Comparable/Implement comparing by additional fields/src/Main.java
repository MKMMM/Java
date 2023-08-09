class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        // add your code here!

        int compResult;

        int sizeComp = Integer.compare(getSize(), otherArticle.getSize());
        int titleComp = getTitle().compareTo(otherArticle.getTitle());

        if (sizeComp == 0) {
            compResult = (titleComp >= 0) ? 1 : -1;
        } else {
            compResult = (sizeComp > 0) ? 1 : -1;
        }

        return compResult;
    }
}