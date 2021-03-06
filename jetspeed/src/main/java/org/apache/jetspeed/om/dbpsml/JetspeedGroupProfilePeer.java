package org.apache.jetspeed.om.dbpsml;

// JDK classes
import java.util.List;
import java.sql.Connection;


//Torque classes
import org.apache.torque.util.Criteria;
import org.apache.torque.util.BasePeer;

import org.apache.jetspeed.om.security.Group;
import org.apache.jetspeed.om.profile.Profile;
import org.apache.jetspeed.om.profile.ProfileLocator;
import org.apache.jetspeed.services.psmlmanager.db.DBOperations;
import org.apache.jetspeed.services.psmlmanager.db.DBUtils;
import org.apache.jetspeed.services.psmlmanager.db.DatabasePsmlManager;
import org.apache.jetspeed.services.PsmlManager;
import org.apache.jetspeed.services.Profiler;

/**
  * The skeleton for this class was autogenerated by Torque on:
  *
  * [Mon Sep 10 13:30:53 PDT 2001]
  *
  *  You should add additional methods to this class to meet the
  *  application requirements.  This class will only be generated as
  *  long as it does not already exist in the output directory.
  *
  * @author <a href="mailto:adambalk@cisco.com">Atul Dambalkar</a>
  * @version $Id: JetspeedGroupProfilePeer.java,v 1.13 2003/08/13 04:18:17 taylor Exp $
  */
public class JetspeedGroupProfilePeer
    extends org.apache.jetspeed.om.dbpsml.BaseJetspeedGroupProfilePeer
    implements DBOperations
{

    /**
     * Default constructor.
     */
    public JetspeedGroupProfilePeer() {}


    /**
     * Insert a group profile record in the database table.
     *
     * @param profile Profile object that will be inserted in the database
     * @param connection A database connection to use
     */
    public void insert(Profile profile, Connection connection) throws Exception
    {
        doInsertOrUpdate(profile, INSERT, connection);
    }

    /**
     * Update group profile record from the database table.
     *
     * @param profile Profile object that will be deleted from the database
     * @param connection A database connection to use
     */
    public void update(Profile profile, Connection connection) throws Exception
    {
        doInsertOrUpdate(profile, UPDATE, connection);
    }


    private void doInsertOrUpdate(Profile profile, int operation, Connection connection)
                           throws Exception
    {
        JetspeedGroupProfile groupProfile = new JetspeedGroupProfile();
        DatabasePsmlManager service = (DatabasePsmlManager)PsmlManager.getService();

        groupProfile.setGroupName(profile.getGroup().getName());
        groupProfile.setMediaType(profile.getMediaType());
        
		String language = profile.getLanguage();
		if(language != null && (!language.equals("-1")))
		{
			groupProfile.setLanguage(language);
		}
		else
		{
			groupProfile.setLanguage(null);
		}

		String country = profile.getCountry();
		if(country != null && (!country.equals("-1")))
		{
			groupProfile.setCountry(country);
		}
		else
		{
			groupProfile.setCountry(null);
		}
        String name = profile.getName();
        if (name == null || name.equals(""))
        {
            profile.setName(Profiler.FULL_DEFAULT_PROFILE);
        }
        else if (!name.endsWith(Profiler.DEFAULT_EXTENSION))
        {
            profile.setName(name + Profiler.DEFAULT_EXTENSION);
        }
        groupProfile.setPage(profile.getName());
        groupProfile.setProfile(DBUtils.portletsToBytes(
                                        profile.getDocument().getPortlets(), service.getMapping()));

        if (operation == INSERT)
        {
            super.doInsert(groupProfile, connection);
        }
        else if (operation == UPDATE)
        {
            Criteria values = buildCriteria(groupProfile);
            Criteria select = buildCriteria(groupProfile);
            select.remove(PROFILE);
            BasePeer.doUpdate( select, values, connection );
        }
    }


    /**
     * Delete group profile record from the database table.
     *
     * @param profile Profile object that will be deleted from the database
     * @param connection A database connection to use
     */
    public void delete(ProfileLocator locator, Connection connection) throws Exception
    {
        super.doDelete(buildCriteria(locator), connection);
    }

    /**
     * Select group profile record from the database table for the given
     * locator object.
     *
     * @param locator ProfileLocator object that will be used to select required
     * profile from the database
     * @param connection A database connection to use
     * @return List of records that statisfy the given locator criteria.
     */
    public List select(ProfileLocator locator, Connection connection) throws Exception
    {
        return super.doSelect(buildCriteria(locator), connection);
    }

    /**
     * Select group profile record from the database table for the given
     * locator object and return list ordered by primary key..
     *
     * @param locator ProfileLocator object that will be used to select required
     * profile from the database
     * @param connection A database connection to use
     * @return List of records that statisfy the given locator criteria.
     */
    public List selectOrdered(ProfileLocator locator, Connection connection) throws Exception
    {
        Criteria criteria = buildCriteria(locator);

        criteria.addAscendingOrderByColumn(GROUP_NAME);
        criteria.addAscendingOrderByColumn(MEDIA_TYPE);
        criteria.addAscendingOrderByColumn(LANGUAGE);
        criteria.addAscendingOrderByColumn(COUNTRY);
        criteria.addAscendingOrderByColumn(PAGE);

        return super.doSelect(criteria, connection);
    }

    /**
     * Delete all records from the database table for a group.
     *
     * @param group Group object for which all the records will be deleted from the database
     * @param connection A database connection to use
     */
    public void delete(Group group, Connection connection) throws Exception
    {
        Criteria criteria = new Criteria();

        criteria.add(GROUP_NAME, group.getName());

        super.doDelete(criteria, connection);
    }


    /*
     * Build criteria for selecting, deleting groups
     *
     */
    protected Criteria buildCriteria(ProfileLocator locator)
    {
        Criteria criteria = new Criteria();

        String mediaType = locator.getMediaType();
        String language = locator.getLanguage();
        String country = locator.getCountry();
        String pageName = locator.getName();
        String groupName = null;

        Group group = locator.getGroup();
        if (group != null) {
            groupName = group.getName();
        }

        if (groupName != null && groupName.length() > 0)
        {
            criteria.add(GROUP_NAME, groupName);
        }

        if (pageName != null && pageName.length() > 0)
        {
            criteria.add(PAGE, pageName);
        }

        if (mediaType != null && mediaType.length() > 0)
        {
            criteria.add(MEDIA_TYPE, locator.getMediaType());
        }

		if (language != null && language.length() > 0 && (!language.equals("-1")))
		{
			criteria.add(LANGUAGE, language);
		} 
		else if(language != null && language.equals("-1"))
		{
			criteria.add(LANGUAGE, null);	
		}

		if (country != null && country.length() > 0 && (!country.equals("-1")))
		{
			criteria.add(COUNTRY, country);
		}
		else if(country != null && country.equals("-1"))
		{
			criteria.add(COUNTRY, null);	
		}
        
        return criteria;
    }

}

