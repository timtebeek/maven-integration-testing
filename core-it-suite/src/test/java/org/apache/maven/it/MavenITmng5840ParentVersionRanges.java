package org.apache.maven.it;

import org.apache.maven.shared.verifier.util.ResourceExtractor;
import org.apache.maven.shared.verifier.Verifier;

import java.io.File;

import org.junit.jupiter.api.Test;

public class MavenITmng5840ParentVersionRanges
    extends AbstractMavenIntegrationTestCase
{
    public MavenITmng5840ParentVersionRanges()
    {
        super( "[3.3,)" );
    }

    @Test
    public void testParentRangeRelativePathPointsToWrongVersion()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/mng-5840-relative-path-range-negative" );

        Verifier verifier = newVerifier( new File( testDir, "parent-1" ).getAbsolutePath(), "remote" );
        verifier.addCliArgument( "install" );
        verifier.execute();
        verifier.verifyErrorFreeLog();

        verifier = newVerifier( new File( testDir, "child" ).getAbsolutePath(), "remote" );
        verifier.addCliArgument( "validate" );
        verifier.execute();
        verifier.verifyErrorFreeLog();
    }

    @Test
    public void testParentRangeRelativePathPointsToCorrectVersion()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/mng-5840-relative-path-range-positive" );

        Verifier verifier = newVerifier( new File( testDir, "parent-1" ).getAbsolutePath(), "remote" );
        verifier.addCliArgument( "install" );
        verifier.execute();
        verifier.verifyErrorFreeLog();

        verifier = newVerifier( new File( testDir, "child" ).getAbsolutePath(), "remote" );
        verifier.addCliArgument( "validate" );
        verifier.execute();
        verifier.verifyErrorFreeLog();
    }

}
