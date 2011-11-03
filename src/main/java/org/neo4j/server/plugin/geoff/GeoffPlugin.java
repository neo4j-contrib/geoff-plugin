/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.plugin.geoff;

import java.io.IOException;
import java.util.Map;

import org.neo4j.cypher.SyntaxException;
import org.neo4j.geoff.BadDescriptorException;
import org.neo4j.geoff.GEOFFLoader;
import org.neo4j.geoff.UnknownNodeException;
import org.neo4j.geoff.UnknownRelationshipException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.server.plugins.Description;
import org.neo4j.server.plugins.Name;
import org.neo4j.server.plugins.Parameter;
import org.neo4j.server.plugins.PluginTarget;
import org.neo4j.server.plugins.ServerPlugin;
import org.neo4j.server.plugins.Source;
import org.neo4j.server.rest.repr.Representation;
import org.neo4j.server.rest.repr.ValueRepresentation;

/* This is a class that will represent a server side
 * Gremlin plugin and will return JSON
 * for the following use cases:
 * Add/delete vertices and edges from the graph.
 * Manipulate the graph indices.
 * Search for elements of a graph.
 * Load graph data from a file or URL.
 * Make use of JUNG algorithms.
 * Make use of SPARQL queries over OpenRDF-based graphs.
 * and much, much more.
 */

@Description( "Plugin to handle GEOFF data insertion and emits" )
public class GeoffPlugin extends ServerPlugin
{

    @Name( "insert" )
    @Description( "receive and add a graph encoded in GEOFF" )
    @PluginTarget( GraphDatabaseService.class )
    public Representation executeScript(
            @Source final GraphDatabaseService neo4j,
            @Description( "The geoff" ) @Parameter( name = "geoff", optional = false ) Map geoff)
            throws SyntaxException
    {
        try
        {
            GEOFFLoader.loadIntoNeo4j(geoff, neo4j);
        }
        catch ( BadDescriptorException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( UnknownNodeException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( UnknownRelationshipException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ValueRepresentation.string( "OK." );
    }

}
