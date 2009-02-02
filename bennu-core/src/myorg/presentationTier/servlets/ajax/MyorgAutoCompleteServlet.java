/*
 * @(#)MyorgAutoCompleteServlet.java
 *
 * Copyright 2009 Instituto Superior Tecnico, João Figueiredo, Luis Cruz, Paulo Abrantes, Susana Fernandes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the MyOrg web application infrastructure.
 *
 *   MyOrg is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.*
 *
 *   MyOrg is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with MyOrg. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package myorg.presentationTier.servlets.ajax;

import java.util.Collection;
import java.util.Map;

import myorg.presentationTier.renderers.autoCompleteProvider.AutoCompleteProvider;
import pt.ist.fenixWebFramework.servlets.ajax.AutoCompleteServlet;

public class MyorgAutoCompleteServlet extends AutoCompleteServlet {

    @Override
    protected Collection getSearchResult(Map<String, String> argsMap, String value, int maxCount) {
	AutoCompleteProvider provider = getProvider(argsMap.get("provider"));
	return provider.getSearchResults(argsMap, value, maxCount);
    }

    private AutoCompleteProvider getProvider(String providerClass) {
	try {
	    Class provider = Class.forName(providerClass);
	    return (AutoCompleteProvider) provider.newInstance();
	} catch (Exception e) {
	    throw new RuntimeException("cannot find provider " + providerClass);
	}
    }

}
