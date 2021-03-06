/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class ScheduledPost extends APINode {
  @SerializedName("admin_creator")
  private User mAdminCreator = null;
  @SerializedName("creation_time")
  private String mCreationTime = null;
  @SerializedName("feed_audience_description")
  private String mFeedAudienceDescription = null;
  @SerializedName("feed_targeting")
  private Targeting mFeedTargeting = null;
  @SerializedName("id")
  private String mId = null;
  @SerializedName("message")
  private String mMessage = null;
  @SerializedName("modified_time")
  private String mModifiedTime = null;
  @SerializedName("og_action_summary")
  private String mOgActionSummary = null;
  @SerializedName("permalink_url")
  private String mPermalinkUrl = null;
  @SerializedName("place")
  private Place mPlace = null;
  @SerializedName("privacy_description")
  private String mPrivacyDescription = null;
  @SerializedName("promotion_info")
  private Object mPromotionInfo = null;
  @SerializedName("scheduled_publish_time")
  private String mScheduledPublishTime = null;
  @SerializedName("story_token")
  private String mStoryToken = null;
  @SerializedName("thumbnail")
  private String mThumbnail = null;
  @SerializedName("video_id")
  private String mVideoId = null;
  protected static Gson gson = null;

  public ScheduledPost() {
  }

  public String getId() {
    return getFieldId().toString();
  }
  public static ScheduledPost loadJSON(String json, APIContext context) {
    ScheduledPost scheduledPost = getGson().fromJson(json, ScheduledPost.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(scheduledPost.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    scheduledPost.context = context;
    scheduledPost.rawValue = json;
    return scheduledPost;
  }

  public static APINodeList<ScheduledPost> parseResponse(String json, APIContext context, APIRequest request) throws MalformedResponseException {
    APINodeList<ScheduledPost> scheduledPosts = new APINodeList<ScheduledPost>(request, json);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          scheduledPosts.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
        };
        return scheduledPosts;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                scheduledPosts.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            scheduledPosts.setPaging(previous, next);
            if (context.hasAppSecret()) {
              scheduledPosts.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              scheduledPosts.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  scheduledPosts.add(loadJSON(entry.getValue().toString(), context));
                }
                break;
              }
            }
            if (!isRedownload) {
              scheduledPosts.add(loadJSON(obj.toString(), context));
            }
          }
          return scheduledPosts;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              scheduledPosts.add(loadJSON(entry.getValue().toString(), context));
          }
          return scheduledPosts;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              scheduledPosts.add(loadJSON(value.toString(), context));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return scheduledPosts;
          }

          // Sixth, check if it's pure JsonObject
          scheduledPosts.clear();
          scheduledPosts.add(loadJSON(json, context));
          return scheduledPosts;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public User getFieldAdminCreator() {
    if (mAdminCreator != null) {
      mAdminCreator.context = getContext();
    }
    return mAdminCreator;
  }

  public ScheduledPost setFieldAdminCreator(User value) {
    this.mAdminCreator = value;
    return this;
  }

  public ScheduledPost setFieldAdminCreator(String value) {
    Type type = new TypeToken<User>(){}.getType();
    this.mAdminCreator = User.getGson().fromJson(value, type);
    return this;
  }
  public String getFieldCreationTime() {
    return mCreationTime;
  }

  public ScheduledPost setFieldCreationTime(String value) {
    this.mCreationTime = value;
    return this;
  }

  public String getFieldFeedAudienceDescription() {
    return mFeedAudienceDescription;
  }

  public ScheduledPost setFieldFeedAudienceDescription(String value) {
    this.mFeedAudienceDescription = value;
    return this;
  }

  public Targeting getFieldFeedTargeting() {
    return mFeedTargeting;
  }

  public ScheduledPost setFieldFeedTargeting(Targeting value) {
    this.mFeedTargeting = value;
    return this;
  }

  public ScheduledPost setFieldFeedTargeting(String value) {
    Type type = new TypeToken<Targeting>(){}.getType();
    this.mFeedTargeting = Targeting.getGson().fromJson(value, type);
    return this;
  }
  public String getFieldId() {
    return mId;
  }

  public ScheduledPost setFieldId(String value) {
    this.mId = value;
    return this;
  }

  public String getFieldMessage() {
    return mMessage;
  }

  public ScheduledPost setFieldMessage(String value) {
    this.mMessage = value;
    return this;
  }

  public String getFieldModifiedTime() {
    return mModifiedTime;
  }

  public ScheduledPost setFieldModifiedTime(String value) {
    this.mModifiedTime = value;
    return this;
  }

  public String getFieldOgActionSummary() {
    return mOgActionSummary;
  }

  public ScheduledPost setFieldOgActionSummary(String value) {
    this.mOgActionSummary = value;
    return this;
  }

  public String getFieldPermalinkUrl() {
    return mPermalinkUrl;
  }

  public ScheduledPost setFieldPermalinkUrl(String value) {
    this.mPermalinkUrl = value;
    return this;
  }

  public Place getFieldPlace() {
    if (mPlace != null) {
      mPlace.context = getContext();
    }
    return mPlace;
  }

  public ScheduledPost setFieldPlace(Place value) {
    this.mPlace = value;
    return this;
  }

  public ScheduledPost setFieldPlace(String value) {
    Type type = new TypeToken<Place>(){}.getType();
    this.mPlace = Place.getGson().fromJson(value, type);
    return this;
  }
  public String getFieldPrivacyDescription() {
    return mPrivacyDescription;
  }

  public ScheduledPost setFieldPrivacyDescription(String value) {
    this.mPrivacyDescription = value;
    return this;
  }

  public Object getFieldPromotionInfo() {
    return mPromotionInfo;
  }

  public ScheduledPost setFieldPromotionInfo(Object value) {
    this.mPromotionInfo = value;
    return this;
  }

  public String getFieldScheduledPublishTime() {
    return mScheduledPublishTime;
  }

  public ScheduledPost setFieldScheduledPublishTime(String value) {
    this.mScheduledPublishTime = value;
    return this;
  }

  public String getFieldStoryToken() {
    return mStoryToken;
  }

  public ScheduledPost setFieldStoryToken(String value) {
    this.mStoryToken = value;
    return this;
  }

  public String getFieldThumbnail() {
    return mThumbnail;
  }

  public ScheduledPost setFieldThumbnail(String value) {
    this.mThumbnail = value;
    return this;
  }

  public String getFieldVideoId() {
    return mVideoId;
  }

  public ScheduledPost setFieldVideoId(String value) {
    this.mVideoId = value;
    return this;
  }




  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public ScheduledPost copyFrom(ScheduledPost instance) {
    this.mAdminCreator = instance.mAdminCreator;
    this.mCreationTime = instance.mCreationTime;
    this.mFeedAudienceDescription = instance.mFeedAudienceDescription;
    this.mFeedTargeting = instance.mFeedTargeting;
    this.mId = instance.mId;
    this.mMessage = instance.mMessage;
    this.mModifiedTime = instance.mModifiedTime;
    this.mOgActionSummary = instance.mOgActionSummary;
    this.mPermalinkUrl = instance.mPermalinkUrl;
    this.mPlace = instance.mPlace;
    this.mPrivacyDescription = instance.mPrivacyDescription;
    this.mPromotionInfo = instance.mPromotionInfo;
    this.mScheduledPublishTime = instance.mScheduledPublishTime;
    this.mStoryToken = instance.mStoryToken;
    this.mThumbnail = instance.mThumbnail;
    this.mVideoId = instance.mVideoId;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<ScheduledPost> getParser() {
    return new APIRequest.ResponseParser<ScheduledPost>() {
      public APINodeList<ScheduledPost> parseResponse(String response, APIContext context, APIRequest<ScheduledPost> request) throws MalformedResponseException {
        return ScheduledPost.parseResponse(response, context, request);
      }
    };
  }
}
