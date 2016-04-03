package com.aliabozaid.grabilitytask.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListOfProductsModel {
    public Feed feed;

    public class Feed {

        public Author author;

        public class Author {

            public Name name;

            public class Name {
                public String label;
            }

            public Uri uri;

            public class Uri {
                public String label;
            }
        }

        public ArrayList<Entry> entry;

        public class Entry {
            @SerializedName("im:name")
            public Name name;

            public class Name {
                public String label;
            }

            public Rights rights;

            public class Rights {
                public String label;
            }

            public Summary summary;

            public class Summary {
                public String label;
            }

            @SerializedName("im:price")
            public Price price;

            public class Price {
                public String label;

                public Attributes attributes;

                public class Attributes {
                    public double amount;
                    public String currency;
                }
            }

            @SerializedName("im:image")
            public ArrayList<Image> image;

            public class Image {
                public String label;

                public Attributes attributes;

                public class Attributes {
                    public int height;
                }
            }

            @SerializedName("im:artist")
            public Artist artist;

            public class Artist {
                public String label;

                public class Attributes {
                    public String href;
                }
            }

            public Title title;

            public class Title {
                public String label;
            }

            public Id id;

            public class Id {
                public String label;

                public Attributes attributes;

                public class Attributes {
                    @SerializedName("im:id")
                    public String id;
                }
            }

            @SerializedName("im:contentType")
            public ContentType contentType;

            public class ContentType {
                public Attributes attributes;

                public class Attributes {
                    public String term;
                    public String label;
                }
            }

            public Category category;

            public class Category {
                public Attributes attributes;

                public class Attributes {
                    @SerializedName("im:id")
                    public String id;
                    public String term;
                    public String scheme;
                    public String label;
                }
            }

            @SerializedName("im:releaseDate")
            public ReleaseDate releaseDate;

            public class ReleaseDate {
                public String label;
                public Attributes attributes;

                public class Attributes {
                    public String label;
                }
            }
        }

    }
}
