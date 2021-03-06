/**********************************************************************
Copyright (c) 2016 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
     ...
 **********************************************************************/
package org.datanucleus.api.rest.jsonobject;

import org.datanucleus.NucleusContext;
import org.datanucleus.api.rest.orgjson.JSONException;
import org.datanucleus.api.rest.orgjson.JSONObject;
import org.datanucleus.util.ClassUtils;

/**
 * Handler to convert a JSON object to Google AppEngine User object.
 */
public class GoogleAppEngineUserHandler implements UserTypeJSONHandler
{
    public Object fromJSON(final JSONObject jsonobj, NucleusContext nucCtx)
    {
        Class cls = nucCtx.getClassLoaderResolver(null).classForName("com.google.appengine.api.users.User");
        String email = null;
        String authDomain = null;
        try
        {
            email = jsonobj.getString("email");
        }
        catch (JSONException e)
        {
            // should not happen if the field exists
        }
        try
        {
            authDomain = jsonobj.getString("authDomain");
        }
        catch (JSONException e)
        {
            // should not happen if the field exists
        }
        return ClassUtils.newInstance(cls, new Class[]{String.class, String.class}, new String[]{email, authDomain});
    }
}
