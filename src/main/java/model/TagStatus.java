package model;

public enum TagStatus
{
    ACTIVE
            {
                public String printDirection()
                {
                    String message = "Tag is active";
                    return message;
                }
            },
    DELETED
            {
                public String printDirection()
                {
                    String message = "Tag is deleted";
                    return message;
                }
            };
}
