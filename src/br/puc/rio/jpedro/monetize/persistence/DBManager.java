package br.puc.rio.jpedro.monetize.persistence;

import android.content.Context;
import android.util.Log;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import java.io.IOException;

public class DBManager
{
    private static final String DB4OFILENAME = "monetize.db4o";
    private static ObjectContainer container = null;
    private Context context;

    public DBManager ( Context ctx )
    {
        context = ctx;
    }

    /**
     * Create, open and close the database
     */
    public ObjectContainer db ()
    {
        try
        {
            if ( container == null || container.ext().isClosed() )
                container = Db4oEmbedded.openFile(dbConfig(), db4oDBFullPath(context));
        }
        catch ( Exception ex1 )
        {
            Log.e(DBManager.class.getName(), ex1.toString());
        }
        finally
        {
            return container;
        }
    }

    /**
     * Configure the behavior of the database
     */
    private EmbeddedConfiguration dbConfig() throws IOException
    {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        // TODO: Specify tuning configurations
        return configuration;
    }

    /**
     * Returns the path for the database location
     */
    private String db4oDBFullPath(Context ctx)
    {
        return ctx.getDir("data", 0) + "/" + DB4OFILENAME;
    }

    /**
     * Closes the database
     */
    public void close()
    {
        if (container != null)
            container.close();
    }

    /**
     * Closes the database when obj is killed from VM
     */
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        this.close();
    }
}