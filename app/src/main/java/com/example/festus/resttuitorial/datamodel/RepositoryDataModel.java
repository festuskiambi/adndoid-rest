package com.example.festus.resttuitorial.datamodel;

import com.squareup.moshi.Json;

/**
 * Created by Festus Kiambi on 11/24/18.
 */

public class RepositoryDataModel {

    final String description;

    @Json(name = "created_at")
    final String created_at;
    final Owner owner;

    public RepositoryDataModel(Owner owner, String description, String created_at) {
        this.owner = owner;
        this.description = description;
        this.created_at = created_at;
    }

    public String getAvatarUrl() {
        return owner.getAvatar_url();
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {

        return created_at;
    }

    static class Owner {
        final String avatar_url;

        public Owner(String avatar_url) {

            this.avatar_url = avatar_url;
        }

        public String getAvatar_url() {

            return avatar_url;
        }
    }
}
