/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.ublpe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.error.list.IErrorList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.xml.serialize.read.DOMReader;
import com.helger.xml.serialize.read.DOMReaderSettings;

import sunat.names.specification.ubl.peru.schema.xsd.summarydocuments_1.SummaryDocumentsType;
import sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.VoidedDocumentsType;

/**
 * Some cross functionality testing
 *
 * @author Philip Helger
 */
public final class UBLPEFuncTest
{
  @Test
  public void testReadAndWriteCancelUserAccount ()
  {
    for (final String sFilename : MockUBLPETestDocuments.getUBLPETestDocuments (EUBLPEDocumentType.SUMMARY_DOCUMENTS))
    {
      // Read
      final Document aDoc = DOMReader.readXMLDOM (new ClassPathResource (sFilename),
                                                  new DOMReaderSettings ().setSchema (EUBLPEDocumentType.SUMMARY_DOCUMENTS.getSchema ()));
      assertNotNull (sFilename, aDoc);
      final SummaryDocumentsType aUBLObject = UBLPEReader.summaryDocuments ().read (aDoc);
      assertNotNull (sFilename, aUBLObject);

      // Validate
      IErrorList aErrors = UBLPEValidator.summaryDocuments ().validate (aUBLObject);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());

      // write again
      final Document aDoc2 = UBLPEWriter.summaryDocuments ().getAsDocument (aUBLObject);
      assertNotNull (aDoc2);
      assertEquals (aDoc.getDocumentElement ().getNamespaceURI (), aDoc2.getDocumentElement ().getNamespaceURI ());
      assertEquals (aDoc.getDocumentElement ().getLocalName (), aDoc2.getDocumentElement ().getLocalName ());

      // read again
      final SummaryDocumentsType aUBLObject2 = UBLPEReader.summaryDocuments ().read (aDoc2);
      assertNotNull (sFilename, aUBLObject2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aUBLObject, aUBLObject2);

      // Validate
      aErrors = UBLPEValidator.summaryDocuments ().validate (aUBLObject2);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());
    }

    // Validate
    final IErrorList aErrors = UBLPEValidator.summaryDocuments ().validate (new SummaryDocumentsType ());
    assertNotNull (aErrors);
    assertTrue (aErrors.containsAtLeastOneError ());
  }

  @Test
  public void testReadAndWriteVoidedDocuments ()
  {
    for (final String sFilename : MockUBLPETestDocuments.getUBLPETestDocuments (EUBLPEDocumentType.VOIDED_DOCUMENTS))
    {
      // Read
      final Document aDoc = DOMReader.readXMLDOM (new ClassPathResource (sFilename),
                                                  new DOMReaderSettings ().setSchema (EUBLPEDocumentType.VOIDED_DOCUMENTS.getSchema ()));
      assertNotNull (sFilename, aDoc);
      final VoidedDocumentsType aUBLObject = UBLPEReader.voidedDocuments ().read (aDoc);
      assertNotNull (sFilename, aUBLObject);

      // Validate
      IErrorList aErrors = UBLPEValidator.voidedDocuments ().validate (aUBLObject);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());

      // write again
      final Document aDoc2 = UBLPEWriter.voidedDocuments ().getAsDocument (aUBLObject);
      assertNotNull (aDoc2);
      assertEquals (aDoc.getDocumentElement ().getNamespaceURI (), aDoc2.getDocumentElement ().getNamespaceURI ());
      assertEquals (aDoc.getDocumentElement ().getLocalName (), aDoc2.getDocumentElement ().getLocalName ());

      // read again
      final VoidedDocumentsType aUBLObject2 = UBLPEReader.voidedDocuments ().read (aDoc2);
      assertNotNull (sFilename, aUBLObject2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aUBLObject, aUBLObject2);

      // Validate
      aErrors = UBLPEValidator.voidedDocuments ().validate (aUBLObject2);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());
    }

    // Validate
    final IErrorList aErrors = UBLPEValidator.voidedDocuments ().validate (new VoidedDocumentsType ());
    assertNotNull (aErrors);
    assertTrue (aErrors.containsAtLeastOneError ());
  }
}
